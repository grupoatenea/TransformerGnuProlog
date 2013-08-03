package edu.unal.DTO;

import edu.unal.model.enums.SolverEditorType;

public class TransformerInDTO {
	private String inputPath;
	private SolverEditorType solverEditorType;
	private String outputPath;
	
	
	/**
	 * @return the solverEditorType
	 */
	public SolverEditorType getSolverEditorType() {
		return solverEditorType;
	}
	/**
	 * @param solverEditorType the solverEditorType to set
	 */
	public void setSolverEditorType(SolverEditorType solverEditorType) {
		this.solverEditorType = solverEditorType;
	}
	/**
	 * @return the outputPath
	 */
	public String getOutputPath() {
		return outputPath;
	}
	/**
	 * @param outputPath the outputPath to set
	 */
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}
	/**
	 * @return the inputPath
	 */
	public String getInputPath() {
		return inputPath;
	}
	/**
	 * @param inputPath the inputPath to set
	 */
	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}





}
