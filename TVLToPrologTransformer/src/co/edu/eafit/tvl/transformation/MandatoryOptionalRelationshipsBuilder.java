package co.edu.eafit.tvl.transformation;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import be.ac.info.fundp.TVLParser.symbolTables.FeatureSymbol;

public class MandatoryOptionalRelationshipsBuilder implements GNUPrologBuilder {
	
	private List<FeatureSymbol> features;

	public MandatoryOptionalRelationshipsBuilder(List<FeatureSymbol> features) {
		this.features = features;
	}

	@Override
	public String build() {
		StringBuilder sb = new StringBuilder();
		for (FeatureSymbol feature : features) {
			if ( feature.isRoot() ){
				sb.append(GNUPrologNamesContainer.getInstance().getFeatureName(feature)).append( " #= 1,\n" );
			}

			if ( hasNotCardinality(feature) ) {
				// Do nothing. Doesn't matter.
			} else if ( hasAndDecomposition(feature) ) {
				Iterator<Map.Entry<String, FeatureSymbol>> it = feature.getChildrenFeatures().entrySet().iterator();
		        while (it.hasNext()) {
		        	FeatureSymbol childrenFeature = it.next().getValue();
		        	if ( childrenFeature.isOptionnal() ) {
						sb.append(GNUPrologNamesContainer.getInstance().getFeatureName(feature)).append( " #>= " ).append(GNUPrologNamesContainer.getInstance().getFeatureName(childrenFeature)).append(",\n");
					} else {
						sb.append(GNUPrologNamesContainer.getInstance().getFeatureName(feature)).append( " #= " ).append(GNUPrologNamesContainer.getInstance().getFeatureName(childrenFeature)).append(",\n");
					}
				}
			} else if ( hasGroupCardinality(feature) ) {
				StringBuilder sbGroupCardinality = new StringBuilder();
				Iterator<Map.Entry<String, FeatureSymbol>> it = feature.getChildrenFeatures().entrySet().iterator();
				if ( it.hasNext() ){
					sbGroupCardinality.append( GNUPrologNamesContainer.getInstance().getFeatureName( it.next().getValue() ) );
				}
		        while (it.hasNext()) {
		        	FeatureSymbol childrenFeature = it.next().getValue();
		        	sbGroupCardinality.append( " + " ).append( GNUPrologNamesContainer.getInstance().getFeatureName(childrenFeature) );
		        }
				String gnuProloegMinRange = new StringBuilder().append( sbGroupCardinality.toString() ).append(" #>= ").append( feature.getMinCardinality() ).append( " * ").append(GNUPrologNamesContainer.getInstance().getFeatureName( feature )).append(",\n").toString();
				String gnuProloegMaxRange = new StringBuilder().append( sbGroupCardinality.toString() ).append(" #=< ").append( feature.getMaxCardinality() ).append( " * ").append(GNUPrologNamesContainer.getInstance().getFeatureName( feature )).append(",\n").toString();
				sb.append(gnuProloegMinRange);
				sb.append(gnuProloegMaxRange);
			}
		}
		sb.append("\n");
		return sb.toString();
	}
	
	private boolean hasGroupCardinality(FeatureSymbol feature) {
		return feature.getMinCardinality() != feature.getMaxCardinality();
	}

	private boolean hasAndDecomposition(FeatureSymbol feature) {
		return feature.getMinCardinality() == feature.getMaxCardinality();
	}

	private boolean hasNotCardinality(FeatureSymbol feature) {
		return feature.getMinCardinality() == 0 && feature.getMaxCardinality() == 0;
	}
}