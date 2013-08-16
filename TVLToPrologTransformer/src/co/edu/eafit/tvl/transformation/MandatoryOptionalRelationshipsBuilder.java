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
				appendRootConstraint(sb, feature);
			}
			if ( hasNotGroupCardinality(feature) ) {
				// Do nothing. Doesn't matter.
			} else if ( hasAndDecomposition(feature) ) {
				appendAndDecompositionConstraint(sb, feature);
			} else if ( hasXorDecompositions(feature) ) {
				appendXorDecompositionsConstraints(sb, feature);
			} else if ( hasOrDecompositions(feature) ) {
				appendOrDecompositionsConstraints(sb, feature);
			}
		}
		appendLineJump(sb);
		return sb.toString();
	}

	private void appendLineJump(StringBuilder sb) {
		sb.append("\n");
	}

	private void appendRootConstraint(StringBuilder sb, FeatureSymbol feature) {
		sb.append(GNUPrologNamesContainer.getInstance().getFeatureName(feature)).append( " #= 1,\n" );
	}

	private void appendAndDecompositionConstraint(StringBuilder sb,
			FeatureSymbol feature) {
		Iterator<Map.Entry<String, FeatureSymbol>> it = feature.getChildrenFeatures().entrySet().iterator();
		while (it.hasNext()) {
			FeatureSymbol childrenFeature = it.next().getValue();
			if ( childrenFeature.isOptionnal() ) {
				sb.append(GNUPrologNamesContainer.getInstance().getFeatureName(feature)).append( " #>= " ).append(GNUPrologNamesContainer.getInstance().getFeatureName(childrenFeature)).append(",\n");
			} else {
				sb.append(GNUPrologNamesContainer.getInstance().getFeatureName(feature)).append( " #= " ).append(GNUPrologNamesContainer.getInstance().getFeatureName(childrenFeature)).append(",\n");
			}
		}
	}

	private void appendOrDecompositionsConstraints(StringBuilder sb,
			FeatureSymbol feature) {
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

	private void appendXorDecompositionsConstraints(StringBuilder sb,
			FeatureSymbol feature) {
		StringBuilder sbGroupCardinality = new StringBuilder();
		Iterator<Map.Entry<String, FeatureSymbol>> it = feature.getChildrenFeatures().entrySet().iterator();
		if ( it.hasNext() ){
			sbGroupCardinality.append( GNUPrologNamesContainer.getInstance().getFeatureName( it.next().getValue() ) );
		}
		while (it.hasNext()) {
			FeatureSymbol childrenFeature = it.next().getValue();
			sbGroupCardinality.append( " + " ).append( GNUPrologNamesContainer.getInstance().getFeatureName(childrenFeature) );
		}
		String gnuProloegXorDecompositionsConstraint = new StringBuilder().append( sbGroupCardinality.toString() ).append(" #= ").append( feature.getMinCardinality() ).append(",\n").toString();
		sb.append( gnuProloegXorDecompositionsConstraint );
	}
	
	private boolean hasOrDecompositions(FeatureSymbol feature) {
		return feature.getMinCardinality() != feature.getMaxCardinality();
	}

	private boolean hasAndDecomposition(FeatureSymbol feature) {
		return feature.getMinCardinality() == feature.getMaxCardinality() && feature.getMinCardinality() != 1;
	}
	
	private boolean hasXorDecompositions(FeatureSymbol feature) {
		return feature.getMinCardinality() == feature.getMaxCardinality() && feature.getMinCardinality() == 1;
	}

	private boolean hasNotGroupCardinality(FeatureSymbol feature) {
		return feature.getMinCardinality() == 0 && feature.getMaxCardinality() == 0;
	}
}