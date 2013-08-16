package co.edu.eafit.tvl.transformation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import co.edu.eafit.tvl.exception.TransformationException;

public abstract class TVLToGNUPrologTransformer {
	
	public abstract void buildHeader();
	public abstract void buildFeaturesList();
	public abstract void buildAttributesList();
	public abstract void buildFeaturesDomainValues();
	public abstract void buildAttributesDomainValues();
	public abstract void buildMandatoryOptionalRelations();
	public abstract void buildConstraints();
	public abstract void buildFooter();
	public abstract void save() throws IOException;
	
	public final void transform() throws IOException{
		buildHeader();
		buildFeaturesList();
		buildAttributesList();
		buildFeaturesDomainValues();
		buildAttributesDomainValues();
		buildMandatoryOptionalRelations();
		buildConstraints();
		buildFooter();
		save();
	}
	
	public static void main(String[] args) throws TransformationException, IOException {
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter TVL input file:");
		String tvlInputFile = bufferRead.readLine();
		System.out.println("Enter GNUProlog output file:");
		String gnuPrologOutputFile = bufferRead.readLine();
		TVLToGNUPrologTransformer transformer = new TVLToGNUPrologTransformerImpl(tvlInputFile, gnuPrologOutputFile);
		transformer.transform();
		System.out.println("Transformation complete!. Verify the output file.");
	}
	
}
