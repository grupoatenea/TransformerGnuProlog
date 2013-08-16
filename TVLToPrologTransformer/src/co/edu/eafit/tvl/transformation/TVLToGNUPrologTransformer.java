package co.edu.eafit.tvl.transformation;

import java.io.IOException;

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
	
}
