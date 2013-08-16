package co.edu.eafit.tvl.expression;

import java.util.Vector;

import be.ac.info.fundp.TVLParser.SyntaxTree.Expression;
import be.ac.info.fundp.TVLParser.SyntaxTree.QuestExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.SumAggExpression;
import co.edu.eafit.tvl.transformation.GNUPrologNamesContainer;

public class GNUPrologSumAggExpression implements GNUPrologExpression {

	private SumAggExpression expression;

	public GNUPrologSumAggExpression(SumAggExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		StringBuilder sb = new StringBuilder();
		StringBuilder sbReifiedSumExpression = new StringBuilder();
		StringBuilder sbReifiedExpressions = new StringBuilder();
		Vector<Expression> expressions = getSumExpressions( expression );
		appendFirstReifiedExpression(expressions, sbReifiedSumExpression, sbReifiedExpressions);
		appendSubsecuentReifiedExpressions(expressions, sbReifiedSumExpression, sbReifiedExpressions);
		appendEndSumExpression(sbReifiedSumExpression);
		return unionReifiedAllExpressions(sb, sbReifiedSumExpression, sbReifiedExpressions);
	}

	private Vector<Expression> getSumExpressions(SumAggExpression expression) {
		return expression.getExpressionList().getExpressions();
	}

	private void appendSubsecuentReifiedExpressions(
			Vector<Expression> expressions,
			StringBuilder sbReifiedSumExpression,
			StringBuilder sbReifiedExpressions) {
		for ( int i = 1; i < expressions.size(); i++) {
			Expression expressioni = expressions.get(i);
			String reifiedVariablei = GNUPrologNamesContainer.getInstance().getNextReifiedVariable();
			appendSumVariable(sbReifiedSumExpression, reifiedVariablei);
			if ( isQuestExpression(expressioni) ){
				appendQuestExpression(sbReifiedExpressions, reifiedVariablei, expressioni, ",\n");
			} else {
				appendNoQuestExpression(sbReifiedExpressions, expressioni, reifiedVariablei, ",\n");
			}
		}
	}

	private void appendFirstReifiedExpression(
			Vector<Expression> expressions,
			StringBuilder sbReifiedSumExpression,
			StringBuilder sbReifiedExpressions) {
		Expression expression1 = expressions.get(0);
		String reifiedVariable = GNUPrologNamesContainer.getInstance().getNextReifiedVariable();
		appendFirstSumExpression(sbReifiedSumExpression, reifiedVariable);
		if ( isQuestExpression(expression1) ){
			appendQuestExpression(sbReifiedExpressions, reifiedVariable, expression1, "");
		} else {
			appendNoQuestExpression(sbReifiedExpressions, expression1, reifiedVariable, "");
		}
	}

	private boolean isQuestExpression(Expression expression1) {
		return expression1 instanceof QuestExpression;
	}

	private void appendFirstSumExpression(StringBuilder sbReifiedSumExpression,
			String reifiedVariable) {
		sbReifiedSumExpression.append( reifiedVariable );
	}

	private String unionReifiedAllExpressions(StringBuilder sb,
			StringBuilder sbReifiedSumExpression,
			StringBuilder sbReifiedExpressions) {
		return sb.append(sbReifiedSumExpression.toString()).append(sbReifiedExpressions.toString()).toString();
	}

	private StringBuilder appendEndSumExpression(StringBuilder sbReifiedSumExpression) {
		return sbReifiedSumExpression.append(",\n");
	}

	private void appendSumVariable(StringBuilder sbReifiedSumExpression, String reifiedVariable) {
		sbReifiedSumExpression.append(" + " ).append( reifiedVariable );
	}

	private void appendQuestExpression(StringBuilder sbReifiedExpressions, String reifiedVariable, Expression expression, String prefix) {
		QuestExpression questExpressioni = (QuestExpression)expression;
		String gnuQuestExpressioni1 = GNUPrologExpressionTransformer.transform( questExpressioni.getExpression1()).toArithmeticForm();
		String gnuQuestExpressioni2 = GNUPrologExpressionTransformer.transform( questExpressioni.getExpression2()).toArithmeticForm();
		String gnuQuestExpressioni3 = GNUPrologExpressionTransformer.transform( questExpressioni.getExpression3()).toArithmeticForm();
		appendFirstSumExpression(sbReifiedExpressions, prefix);
		sbReifiedExpressions.append( gnuQuestExpressioni1 ).append( " #==> ").append( reifiedVariable ).append(" #= ").append( gnuQuestExpressioni2 ).append(", ");
		sbReifiedExpressions.append("#\\ ").append( gnuQuestExpressioni1 ).append( " #==> ").append( reifiedVariable ).append(" #= ").append( gnuQuestExpressioni3 );
	}

	private void appendNoQuestExpression(StringBuilder sbReifiedExpressions, Expression expression, String reifiedVariable, String prefix) {
		sbReifiedExpressions.append( prefix ).append( reifiedVariable ).append( " #= ").append( GNUPrologExpressionTransformer.transform( expression ).toArithmeticForm() );
	}
	
	
}
