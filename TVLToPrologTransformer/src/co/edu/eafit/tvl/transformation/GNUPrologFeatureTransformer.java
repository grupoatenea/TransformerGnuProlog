package co.edu.eafit.tvl.transformation;

import java.util.Iterator;
import java.util.Map;

import be.ac.info.fundp.TVLParser.symbolTables.FeatureSymbol;

public class GNUPrologFeatureTransformer extends GNUPrologVariableTransformer {
	
	private FeatureSymbol feature;
	private String gnuPrologName;

	public GNUPrologFeatureTransformer(FeatureSymbol feature) {
		this.feature = feature;
		gnuPrologName = feature.getID();
	}

	public String getGNUPrologName() {
		if ( feature.getParentsFeature() == null ) return gnuPrologName;
		Iterator<Map.Entry<String, FeatureSymbol>> it = feature.getParentsFeature().entrySet().iterator();
		while(it.hasNext()){
			String gnuPrologParentName = new GNUPrologFeatureTransformer(it.next().getValue()).getGNUPrologName();
			gnuPrologName = gnuPrologParentName.concat("_").concat(gnuPrologName);
		}
		return gnuPrologName;
	}
	
	

}
