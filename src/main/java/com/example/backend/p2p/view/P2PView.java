package com.example.backend.p2p.view;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Observable;

import com.example.backend.csvIO.csvIO;
import com.example.backend.p2p.state.P2PState;
import com.example.backend.simulator.SimView;


public class P2PView extends SimView {
    private String Filepath = "";
    private String result = "";
    private ArrayList<csvIO> csv = new ArrayList<>();
    double simTime;
    double nextAvgTime;

	/**
	 *
	 * @param filepath
	 * @param nodeAmount
	 * @param simTime
	 */
    public P2PView(String filepath, int nodeAmount, double simTime) {
    	for(int i = 0; i<nodeAmount; i++) {
    		csvIO temp = new csvIO();
    		temp.setFile(filepath + i + ".csv");
    		temp.matrixNewLine();
    		temp.matrixAdd("TIME,EVENT_TYPE,EVENT_SOURCE,EVENT_DESTINATION,MAP,INTERFERENCE");
    		csv.add(temp);
    	}
    	csvIO temp = new csvIO();
    	temp.setFile(filepath + "average.csv");
    	temp.matrixNewLine();
    	temp.matrixAdd("TIME,AVG_MAP");
    	temp.matrixNewLine();
    	temp.matrixAdd("0,0");
    	csv.add(temp);
    	this.simTime=simTime;
    	this.nextAvgTime = simTime/10;

    }

	/**
	 * This method is being called every time an event happens.
	 * If the simulator is running generateProgress is called and
	 * saves the parameters. If the simulator is not running the
	 * result is being printed to the files.
	 * @param o
	 * @param arg
	 */
    @Override
    public void update(Observable o, Object arg){
        P2PState state = (P2PState) o;
        if(state.simulatorIsRunning()){
            result += generateProgress(state);
        }
        else{
           // printFile(Filepath, true);
        	try {
				for(int i = 0; i<csv.size(); i++) {
					//csv.get(i).matrixAdd(Integer.toString(state.getNode(i).getEventCounter()));
					csv.get(i).saveCsvMatrix();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //printConsole();
        }
    }

	/**
	 * This method saves all the parameters and adds them to
	 * the correct csv matrix.
	 * @param state
	 * @return
	 */
	public String generateProgress(P2PState state) {
    	String result = null;
		/**
		 * This if block computes the average map value
		 * of all nodes.
		 */
		if(state.getElapsedTime() > this.nextAvgTime){
    		this.nextAvgTime += simTime/10;
			csvIO temp = csv.get(csv.size()-1);
			temp.matrixNewLine();
			double mapSum = 0;
			double amount = 0;
			for(int i = 0; i<csv.size()-1;i++){
				double tempMap = state.getNodeMap(i);
				mapSum += tempMap;
				amount++;
			}
			double mapAvg = mapSum/amount;
			temp.matrixAdd(cutDecimals(state.getElapsedTime()));
			temp.matrixAdd(cutDecimals(mapAvg));
		}
    	if(state.getNodeWhoPerformedEvent() != "-") {
    		int node = Integer.parseInt(state.getNodeWhoPerformedEvent());
    		csvIO temp = csv.get(node);

    		temp.matrixNewLine();
    		result ="";
    		result += cutDecimals(state.getElapsedTime());
    		temp.matrixAdd(cutDecimals(state.getElapsedTime()));
    		result += state.getEventDescription();
    		temp.matrixAdd(state.getEventDescription());
    		if(state.getNodeWhoPerformedEvent() != "-") {
				// Adds source
				result += state.getNodeWhoPerformedEvent();
				temp.matrixAdd(state.getNodeWhoPerformedEvent());
    			result += "";
				result +=  cutDecimals(state.getNodeMap(Integer.parseInt(state.getNodeWhoPerformedEvent())));
				result += "";
				result +=  cutDecimals(state.getNodeMap(Integer.parseInt(state.getNodeWhoPerformedEvent())));
				// Adds destination
				temp.matrixAdd(state.getNodeDestination());
				// Adds the mAP value
				temp.matrixAdd(cutDecimals(state.getNodeMap(Integer.parseInt(state.getNodeWhoPerformedEvent()))));
				// Adds the average mAP value for the specific node
				temp.matrixAdd(cutDecimals(state.getMeanMap(Integer.parseInt(state.getNodeWhoPerformedEvent()))));
    	}
    	}
    	return result;
    }

	/**
	 * This method is used to print the result to a regular file
	 * @param filePath designated file path
	 * @param overwrite overwrite current file path
	 */
    @Override
    public void printFile(String filePath, boolean overwrite){
        File file = new File(filePath);
        if (file.isDirectory()){
            return;
        }
        try {
			PrintWriter writer = new PrintWriter(file);
			writer.println(result);
			writer.close();
			System.out.println("File saved to: \n" + filePath);
		} catch (Exception e) {
			System.out.println("An error occured while writing result to file at: \n" + filePath);
		}
    }

	/**
	 * Cuts of decimals
	 * @param d
	 * @return
	 */
	private String cutDecimals(double d) {
		return new DecimalFormat("#.####").format(d);
	}

	/**
	 * This method prints the result to console
	 */
	@Override
	public void printConsole() {
		System.out.println(result);
	}
}