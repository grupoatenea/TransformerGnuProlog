package co.edu.eafit.tvl.transformation;

import java.io.IOException;

public abstract class TVLToGNUPrologTransformer {
	
	public abstract String getHeader();
	public abstract String getFeaturesList();
	public abstract String getAttributesList();
	public abstract String getFeaturesDomainValues();
	public abstract String getAttributesDomainValues();
	public abstract String getMandatoryOptionalRelations();
	public abstract String getConstraints();
	public abstract String getFooter();
	public abstract void save(String gnuFile) throws IOException;
	
	public final void transform() throws IOException{
		StringBuilder sb = new StringBuilder();
		sb.append( getHeader() );
		sb.append( getFeaturesList() );
		sb.append( getAttributesList() );
		sb.append( getFeaturesDomainValues() );
		sb.append( getAttributesDomainValues() );
		sb.append( getMandatoryOptionalRelations() );
		sb.append( getConstraints() );
		sb.append( getFooter() );
		save( sb.toString() );
	}
	
}
