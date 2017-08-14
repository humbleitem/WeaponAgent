package readFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.plaf.basic.BasicComboPopup;

public class Attribute {

	private int arrX;
	private int arrY;
	private int population;
	private int combineRun;
	private boolean initialSolutionSet;
	private String algorithm;
	private int lineNum;
	private double lossRate;
	private int weaponNum;
	private double delayTime;
	private double periodTime;
	private boolean duplicate;
	private double totalTime;
	BufferedReader br;

	public Attribute() {
		// TODO Auto-generated constructor stub
		String line;
		try {
			br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\readFile\\attribute.txt"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void initialAttribute() {
		lineNum = 0;
		readFile();

	}

	public void readFile() {
		String line;
		try {

			while (lineNum != 12) {
				line = br.readLine();
				lineNum++;
				String[] str = line.split(" ");

				switch (str[0]) {

				case "arrx:":
					arrX = Integer.parseInt(str[1]);
					break;
				case "arry:":
					arrY = Integer.parseInt(str[1]);
					break;
				case "population:":
					population = Integer.parseInt(str[1]);
					break;
				case "combineRun:":
					combineRun = Integer.parseInt(str[1]);
					break;
				case "algorithm:":
					algorithm = str[1];
					break;
				case "initialSolutionSet:":
					initialSolutionSet = Boolean.parseBoolean(str[1]);
					break;
				case "lossRate:":
					lossRate = Double.parseDouble(str[1]);
					break;
				case "weaponNum:":
					weaponNum = Integer.parseInt(str[1]);
					break;
				case "delayTime:":
					delayTime = Double.parseDouble(str[1]);
					break;
				case "periodTime:":
					periodTime = Double.parseDouble(str[1]);
					break;
				case "duplicate:":
					duplicate = Boolean.parseBoolean(str[1]);
					break;
				case "totalTime:":
					totalTime = Double.parseDouble(str[1]);
					break;

				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int getArrX() {
		return arrX;
	}

	public int getArrY() {
		return arrY;
	}

	public int getPopulation() {
		return population;
	}
	
	public int getCombineRun() {
		return combineRun;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public boolean getInitialSolutionSet() {
		return initialSolutionSet;
	}

	public double getLossRate() {
		return lossRate;
	}

	public int getWeaponNum() {
		return weaponNum;
	}

	public double getDelayTime() {
		return delayTime;
	}

	public double getPeriodTime() {
		return periodTime;
	}

	public boolean getDuplicate() {
		return duplicate;
	}
	public double getTotalTime(){
		return totalTime;
	}

	public void setDelayTime(String str) {
		delayTime = Double.parseDouble(str);
	}

	public void setPeriodTime(String str) {
		periodTime = Double.parseDouble(str);
	}


	public void setCombineRun(String str) {
		combineRun = Integer.parseInt(str);
	}

	public void setAlgorithm(String str) {
		algorithm = str;
	}

	public void setLossRate(String str) {
		lossRate = Double.parseDouble(str);
	}
	public void setTotalTime(String str){
		totalTime = Double.parseDouble(str);
	}
}
