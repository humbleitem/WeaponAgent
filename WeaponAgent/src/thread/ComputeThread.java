package thread;

import java.lang.ref.SoftReference;

import FIPA.stringsHelper;
import algorithmInterface.AlgorithmFactory;
import algorithmInterface.WeaponAlgorithm;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import other.Instance;
import readFile.Attribute;
import storage.Storage;
import storage.populationManager;

public class ComputeThread extends Thread {

	private Storage storage;
	private Attribute AT;
	private Agent mainAgent;
	private int combineRun;
	private int threadNum;
	private boolean initialSolutionSet;
	private boolean keepRun;
	private int executeTime;

	public ComputeThread(Attribute AT, Storage storage, Agent mainAgent) {

		this.storage = storage;
		this.AT = AT;
		this.combineRun = AT.getCombineRun();
		this.initialSolutionSet = AT.getInitialSolutionSet();
		this.threadNum = AT.getWeaponNum();
		this.mainAgent = mainAgent;
		keepRun = true;
		executeTime = 0;
		

	}

	@Override
	public void run() {

		// set algorithm
		AlgorithmFactory AF = new AlgorithmFactory(AT, storage);
		WeaponAlgorithm weaponAlgorithm = AF.getAlgorithm();

		// set attribute of population
		populationManager populationM = new populationManager(AT);

		// for (int i = 0; i < exchangeNum; i++) {
		while (keepRun) {
			// set algorithmRunTime
			weaponAlgorithm.setStopCondition(combineRun);

			// set population of first run time
			if (executeTime == 0) {
				// if use random population
				if (initialSolutionSet) {
					weaponAlgorithm.setPopulation(populationM.randomPopulation());
				}
				// use regular population
				else {
					weaponAlgorithm.setPopulation(populationM.filePopulation());
				}
				// set population of not first time
			} else {
				// get population from populationManger and compute
				weaponAlgorithm.setPopulation(populationM.getPopulation());

			}

			weaponAlgorithm.findAnswer();

			// add population in the populationManget and wait other thread
			// finish

			// get population from this thread compute
			populationM.storePopulation(weaponAlgorithm.getPopulation());

			// add population from the other thread and minus population of bad
			// performance

			synchronized (storage) {
				if (storage.checkOtherBestAns()) {

					for (Instance instance : storage.getOtherBestAns()) {

						// add instance into our population except original
						// instance

						// according to error rate decide add or not
						if (AT.getLossRate() <= Math.random()) {

							if (AT.getAlgorithm().equals("particleswarm")) {

								weaponAlgorithm.setGlobalBestParticle(instance);
								break;

							} else {
								// add instance from other node of best
								populationM.addPopulation(instance);
							}

						}

					}

				}
				// clear other solution
				storage.clearOtherBestAns();

			}
			
			//add executeTime
			executeTime++;
		}
		System.out.println("*******************************");
		// thread done
		storage.setState(false);

	}

	public void setKeepRun(boolean keepRun) {

		this.keepRun = keepRun;
	}

}
