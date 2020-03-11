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
     */
    public P2PView(String filepath, int nodeAmount, double simTime) {
    	//this.Filepath = filepath;
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
    public String generateProgress(P2PState state) {
    	String result = null;
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
    			result += state.getNodeWhoPerformedEvent();
    			result += "";

    			//source
				result +=  cutDecimals(state.getNodeMap(Integer.parseInt(state.getNodeWhoPerformedEvent())));
				result += "";
				temp.matrixAdd(state.getNodeWhoPerformedEvent());
				//destination
				result +=  cutDecimals(state.getNodeMap(Integer.parseInt(state.getNodeWhoPerformedEvent())));
				temp.matrixAdd(state.getNodeDestination());
				temp.matrixAdd(cutDecimals(state.getNodeMap(Integer.parseInt(state.getNodeWhoPerformedEvent()))));
				temp.matrixAdd(cutDecimals(state.getMeanMap(Integer.parseInt(state.getNodeWhoPerformedEvent()))));
				//temp.matrixAdd(cutDecimals(state.getEventCounter((Integer.parseInt(state.getNodeWhoPerformedEvent())))));


    	}
    	}
    	return result;
    }
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

	private String cutDecimals(double d) {
		return new DecimalFormat("#.####").format(d);
	}
	@Override
	public void printConsole() {
		System.out.println(result);
	}
}