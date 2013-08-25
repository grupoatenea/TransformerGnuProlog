package co.edu.eafit.tvl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import be.ac.info.fundp.TVLParser.SyntaxTree.AbsExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.AndAggExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.AndExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.AvgAggExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.DivideExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.EqualsExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.ExcludesExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.ExpressionList;
import be.ac.info.fundp.TVLParser.SyntaxTree.FalseExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.GEQExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.GreaterExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.IfAndOnlyIfExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.ImpliesExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.InExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.IncludesExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.IntExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.InverseImpliesExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.LEQExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.LongIDExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.LowerExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.MaxAggExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.MinAggExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.MinusExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.MulAggExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.NotExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.OrAggExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.OrExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.ParenthesesExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.PlusExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.QuestExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.RealExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.SetExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.SumAggExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.TimesExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.TrueExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.XorAggExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.ZeroExpression;
import co.edu.eafit.tvl.expression.GNUPrologExpressionTransformer;
import co.edu.eafit.tvl.transformation.GNUPrologNamesContainer;

public class TVLTransformerExpressionToGNUPrologTest {
	
	@Test(expected=IllegalArgumentException.class)
	public void testAbsPlusRealExpression() {
		RealExpression s1 = new RealExpression("2.6");
		RealExpression s2 = new RealExpression("2.9");
		PlusExpression plusExpression = new PlusExpression(s1, s2);
		AbsExpression absExpression = new AbsExpression(plusExpression);
		String gnuAbsExpressionString = GNUPrologExpressionTransformer.transform(absExpression).toArithmeticForm();
		assertEquals ("abs(2.6 + 2.9)", gnuAbsExpressionString);
	}
	
	@Test
	public void testAbsPlusIntExpression() {
		IntExpression s1 = new IntExpression("2");
		IntExpression s2 = new IntExpression("4");
		PlusExpression plusExpression = new PlusExpression(s1, s2);
		AbsExpression absExpression = new AbsExpression(plusExpression);
		String gnuAbsExpressionString = GNUPrologExpressionTransformer.transform(absExpression).toArithmeticForm();
		assertEquals ("abs(2 + 4)", gnuAbsExpressionString);
	}
	
	@Test
	public void testAndAggExpression() {
		IntExpression intExpression1 = new IntExpression("123");
		IntExpression intExpression2 = new IntExpression("5");
		ExpressionList expressionList = new ExpressionList(intExpression1, new ExpressionList(intExpression2));
		AndAggExpression andAggExpression = new AndAggExpression(expressionList);
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(andAggExpression).toArithmeticForm();
		assertEquals ("5,\n123", gnuExpressionString);
	}
	
	@Test
	public void testAndExpression() {
		LowerExpression lowerExpression = new LowerExpression(new IntExpression("123"), new IntExpression("5"));
		GreaterExpression greaterExpression = new GreaterExpression(new IntExpression("12345"), new IntExpression("58"));
		AndExpression andExpression = new AndExpression(lowerExpression, greaterExpression);
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(andExpression).toArithmeticForm();
		assertEquals ("123 #< 5 #/\\ 12345 #> 58", gnuExpressionString);
	}
	
	@Test
	public void testAvgExpression() {
		IntExpression intExpression1 = new IntExpression("123");
		IntExpression intExpression2 = new IntExpression("5");
		ExpressionList expressionList = new ExpressionList(intExpression1, new ExpressionList(intExpression2));
		AvgAggExpression avgAggExpression = new AvgAggExpression(expressionList);
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(avgAggExpression).toArithmeticForm();
		assertEquals ("(5 + 123) / 2", gnuExpressionString);
	}
	
	@Test
	public void testEqualsExpression() {
		GEQExpression geqExpression = new GEQExpression(new LongIDExpression("Car.hp", null), new IntExpression("123"));
		LEQExpression leqExpression = new LEQExpression(new LongIDExpression("Bus.hp", null), new IntExpression("800"));
		EqualsExpression equalsExpression = new EqualsExpression(geqExpression, leqExpression);
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(equalsExpression).toArithmeticForm();
		assertEquals ("Car.hp #>= 123 #==> Bus.hp #=< 800", gnuExpressionString);
	}
	
	@Test
	public void testExcludesExpression() {
		GNUPrologNamesContainer.populate();
		GNUPrologNamesContainer.getInstance().getFeaturesGNUProlog().put(1, "Car_hp");
		GNUPrologNamesContainer.getInstance().getFeaturesGNUProlog().put(2, "Bus_hp");
		LongIDExpression longIDExpresion1 = new LongIDExpression("Car.hp", null);
		LongIDExpression longIDExpresion2 = new LongIDExpression("Bus.hp", null);
		ExcludesExpression excludesExpression = new ExcludesExpression(longIDExpresion1.getLongID(), longIDExpresion2.getLongID(), null);
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(excludesExpression).toArithmeticForm();
		assertEquals ("Car_hp + Bus_hp #=< 1", gnuExpressionString);
	}
	
	@Test
	public void testIncludesExpression() {
		GNUPrologNamesContainer.populate();
		GNUPrologNamesContainer.getInstance().getFeaturesGNUProlog().put(1, "Car_hp");
		GNUPrologNamesContainer.getInstance().getFeaturesGNUProlog().put(2, "Bus_hp");
		LongIDExpression longIDExpresion1 = new LongIDExpression("Car.hp", null);
		LongIDExpression longIDExpresion2 = new LongIDExpression("Bus.hp", null);
		IncludesExpression includesExpression = new IncludesExpression(longIDExpresion1.getLongID(), longIDExpresion2.getLongID(), null);
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(includesExpression).toArithmeticForm();
		assertEquals ("Car_hp #==> Bus_hp", gnuExpressionString);
	}
	
	@Test
	public void testImpliesExpression() {
		LongIDExpression longIDExpresion1 = new LongIDExpression("Car", null);
		LongIDExpression longIDExpresion2 = new LongIDExpression("Bus", null);
		ImpliesExpression impliesExpression = new ImpliesExpression(longIDExpresion1, longIDExpresion2);
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(impliesExpression).toArithmeticForm();
		assertEquals ("Car #==> Bus", gnuExpressionString);
	}
	
	@Test
	public void testQuestExpression() {
		GEQExpression expression1 = new GEQExpression(new LongIDExpression("Car", null), new IntExpression("123"));
		LEQExpression expression2 = new LEQExpression(new LongIDExpression("Bus", null), new IntExpression("800"));
		GreaterExpression expression3 = new GreaterExpression(new IntExpression("1234"), new IntExpression("5"));
		QuestExpression questExpression = new QuestExpression(expression1, expression2, expression3);
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(questExpression).toArithmeticForm();
		assertEquals ("( Car #>= 123 ) #==> ( Bus #=< 800 ), #\\ ( Car #>= 123 ) #==> ( 1234 #> 5 )", gnuExpressionString);
	}
	
	@Test
	public void testZeroExpression() {
		ZeroExpression zeroExpression = new ZeroExpression();
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(zeroExpression).toArithmeticForm();
		assertEquals ("0", gnuExpressionString);
	}

	@Test
	public void testOrExpression() {
		LowerExpression lowerExpression = new LowerExpression(new IntExpression("123"), new IntExpression("5"));
		GreaterExpression greaterExpression = new GreaterExpression(new IntExpression("1234"), new IntExpression("56"));
		OrExpression orExpression = new OrExpression(lowerExpression, greaterExpression);
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(orExpression).toArithmeticForm();
		assertEquals ("123 #< 5 #\\/ 1234 #> 56", gnuExpressionString);
	}
	
	@Test
	public void testNotExpression() {
		NotExpression notExpression = new NotExpression(new LongIDExpression("Car", null));
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(notExpression).toArithmeticForm();
		assertEquals ("#\\ Car", gnuExpressionString);
	}
	

	@Test
	public void testDivideExpression() {
		LongIDExpression expression1 = new LongIDExpression("Car.hp", null);
		IntExpression expression2 = new IntExpression("123");
		DivideExpression divideExpression = new DivideExpression(expression1, expression2);
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(divideExpression).toArithmeticForm();
		assertEquals ("Car.hp / 123", gnuExpressionString);
	}
	
	@Test
	public void testIfAndOnlyIfExpression() {
		LongIDExpression expression1 = new LongIDExpression("Car.hp", null);
		IntExpression expression2 = new IntExpression("123");
		IfAndOnlyIfExpression ifAndOnlyIfExpression = new IfAndOnlyIfExpression(expression1, expression2);
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(ifAndOnlyIfExpression).toArithmeticForm();
		assertEquals ("Car.hp #<=> 123", gnuExpressionString);
	}
	
	@Test
	public void testFalseExpression() {
		FalseExpression falseExpression = new FalseExpression();
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(falseExpression).toArithmeticForm();
		assertEquals ("0", gnuExpressionString);
	}
	
	@Test
	public void testTrueExpression() {
		TrueExpression trueExpression = new TrueExpression();
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(trueExpression).toArithmeticForm();
		assertEquals ("1", gnuExpressionString);
	}
	
	@Test
	public void testInExpressionListValues() {
		IntExpression intExpression1 = new IntExpression("123");
		IntExpression intExpression2 = new IntExpression("5");
		IntExpression realExpression = new IntExpression("68");
		ExpressionList expressionList = new ExpressionList(intExpression1, new ExpressionList(intExpression2));
		SetExpression setExpression = new SetExpression(expressionList, null);
		InExpression inExpression = new InExpression(realExpression, setExpression);
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(inExpression).toArithmeticForm();
		assertEquals ("(68 #= 5; 68 #= 123)", gnuExpressionString);
	}
	
	@Test
	public void testInExpressionRange() {
		IntExpression realExpression = new IntExpression("5");
		SetExpression setExpression = new SetExpression("1", "10", null);
		InExpression inExpression = new InExpression(realExpression, setExpression);
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(inExpression).toArithmeticForm();
		assertEquals ("5 #>= 1, 5 #=< 10", gnuExpressionString);
	}

	@Test
	public void testParanthesisxpression() {
		IntExpression realExpression = new IntExpression("123456");
		ParenthesesExpression parenthesesExpression = new ParenthesesExpression(realExpression);
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(parenthesesExpression).toArithmeticForm();
		assertEquals ("( 123456 )", gnuExpressionString);
	}
	
	@Test
	public void testMultipleParanthesisxpression() {
		LowerExpression lowerExpression = new LowerExpression(new IntExpression("123"), new IntExpression("5"));
		ParenthesesExpression parenthesesLowerExpression = new ParenthesesExpression(lowerExpression);
		GreaterExpression greaterExpression = new GreaterExpression(new IntExpression("123456"), new IntExpression("58"));
		ParenthesesExpression parenthesesGreaterExpression = new ParenthesesExpression(greaterExpression);
		OrExpression orExpression = new OrExpression(parenthesesLowerExpression, parenthesesGreaterExpression);
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(orExpression).toArithmeticForm();
		assertEquals ("( 123 #< 5 ) #\\/ ( 123456 #> 58 )", gnuExpressionString);
	}
	
	@Test
	public void testMaxAggExpression() {
		IntExpression intExpression1 = new IntExpression("123");
		IntExpression intExpression2 = new IntExpression("5");
		IntExpression intExpression3 = new IntExpression("8");
		ExpressionList expressionList = new ExpressionList(intExpression1, new ExpressionList(intExpression2, new ExpressionList(intExpression3)));
		MaxAggExpression maxAggExpression = new MaxAggExpression(expressionList);
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(maxAggExpression).toArithmeticForm();
		assertEquals ("max(max(8, 5), 123)", gnuExpressionString);
	}
	
	@Test
	public void testTwoExpressionsMaxAggExpression() {
		IntExpression intExpression1 = new IntExpression("123");
		IntExpression intExpression2 = new IntExpression("5");
		ExpressionList expressionList = new ExpressionList(intExpression1, new ExpressionList(intExpression2));
		MaxAggExpression maxAggExpression = new MaxAggExpression(expressionList);
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(maxAggExpression).toArithmeticForm();
		assertEquals ("max(5, 123)", gnuExpressionString);
	}
	
	@Test
	public void testFiveExpressionsMaxAggExpression() {
		IntExpression intExpression1 = new IntExpression("123");
		IntExpression intExpression2 = new IntExpression("5");
		IntExpression intExpression3 = new IntExpression("45");
		IntExpression intExpression4 = new IntExpression("53");
		IntExpression intExpression5 = new IntExpression("59");
		ExpressionList expressionList = 
				new ExpressionList(intExpression1, 
						new ExpressionList(intExpression2,
								new ExpressionList(intExpression3, 
										new ExpressionList(intExpression4, 
												new ExpressionList(intExpression5)))));
		MaxAggExpression maxAggExpression = new MaxAggExpression(expressionList);
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(maxAggExpression).toArithmeticForm();
		assertEquals ("max(max(max(max(59, 53), 45), 5), 123)", gnuExpressionString);
	}
	
	@Test
	public void testFiveExpressionsMinAggExpression() {
		IntExpression intExpression1 = new IntExpression("123");
		IntExpression intExpression2 = new IntExpression("5");
		IntExpression intExpression3 = new IntExpression("45");
		IntExpression intExpression4 = new IntExpression("53");
		IntExpression intExpression5 = new IntExpression("59");
		ExpressionList expressionList = 
				new ExpressionList(intExpression1, 
						new ExpressionList(intExpression2,
								new ExpressionList(intExpression3, 
										new ExpressionList(intExpression4, 
												new ExpressionList(intExpression5)))));
		MinAggExpression minAggExpression = new MinAggExpression(expressionList);
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(minAggExpression).toArithmeticForm();
		assertEquals ("min(min(min(min(59, 53), 45), 5), 123)", gnuExpressionString);
	}
	
	@Test
	public void testSumAggExpression() {
		GNUPrologNamesContainer.populate();
		QuestExpression questExpression1 = new QuestExpression(
				new LongIDExpression("Sports", null),
				new LongIDExpression("Sports.weight", null),
				new ZeroExpression());
		QuestExpression questExpression2 = new QuestExpression(
				new LongIDExpression("Family", null),
				new LongIDExpression("Family.weight", null),
				new ZeroExpression());
		ExpressionList expressionList = 
				new ExpressionList(questExpression2, 
						new ExpressionList(questExpression1));
		SumAggExpression sumAggExpression = new SumAggExpression(expressionList);
		EqualsExpression equalsExpression = new EqualsExpression(new LongIDExpression("Car.weight", null), sumAggExpression);
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(equalsExpression).toArithmeticForm();
		assertEquals (
				"Car.weight #> 0 #==> Car.weight #= R1 + R2,\n" + 
				"Sports #==> R1 #= Sports.weight, #\\ Sports #==> R1 #= 0,\n" +
				"Family #==> R2 #= Family.weight, #\\ Family #==> R2 #= 0", gnuExpressionString);
	}
	
	@Test
	public void testMinusExpression() {
		LongIDExpression expression1 = new LongIDExpression("Car.hp", null);
		IntExpression expression2 = new IntExpression("123");
		MinusExpression minusExpression = new MinusExpression(expression1, expression2);
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(minusExpression).toArithmeticForm();
		assertEquals ("Car.hp - 123", gnuExpressionString);
	}
	
	@Test
	public void testMulAggExpression() {
		LongIDExpression expression1 = new LongIDExpression("Car.hp", null);
		IntExpression expression2 = new IntExpression("123");
		ExpressionList expressionList = new ExpressionList(expression1);
		expressionList.getExpressions().add(expression2);
		MulAggExpression mulAggExpression = new MulAggExpression(expressionList);
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(mulAggExpression).toArithmeticForm();
		assertEquals ("Car.hp * 123", gnuExpressionString);
	}
	
	@Test
	public void testOrAggExpression() {
		LongIDExpression expression1 = new LongIDExpression("Car.hp", null);
		IntExpression expression2 = new IntExpression("123");
		IntExpression expression3 = new IntExpression("678");
		ExpressionList expressionList = new ExpressionList(expression1);
		expressionList.getExpressions().add(expression2);
		expressionList.getExpressions().add(expression3);
		OrAggExpression orAggExpression = new OrAggExpression(expressionList);
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(orAggExpression).toArithmeticForm();
		assertEquals ("Car.hp #\\/ 123 #\\/ 678", gnuExpressionString);
	}
	
	@Test
	public void testInverseImpliesExpression() {
		LongIDExpression longIDExpresion1 = new LongIDExpression("Money", null);
		LongIDExpression longIDExpresion2 = new LongIDExpression("job", null);
		InverseImpliesExpression inverseImpliesExpression = new InverseImpliesExpression(longIDExpresion1, longIDExpresion2);
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(inverseImpliesExpression).toArithmeticForm();
		assertEquals ("job #==> Money", gnuExpressionString);
	}
	
	@Test
	public void testTimesExpression() {
		LongIDExpression expression1 = new LongIDExpression("Car.hp", null);
		IntExpression expression2 = new IntExpression("100");
		TimesExpression timesExpression = new TimesExpression(expression1, expression2);
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(timesExpression).toArithmeticForm();
		assertEquals ("Car.hp * 100", gnuExpressionString);
	}
	
	@Test
	public void testXorAggExpression() {
		LongIDExpression expression1 = new LongIDExpression("Car.hp", null);
		LongIDExpression expression2 = new LongIDExpression("Car.color", null);
		LongIDExpression expression3 = new LongIDExpression("Car.value", null);
		
		ExpressionList expresionList = new ExpressionList(expression1);
		ExpressionList expresionList2 = new ExpressionList(expression2, expresionList);
		ExpressionList expresionListFinal = new ExpressionList(expression3, expresionList2);
		
		XorAggExpression xorAggExpression = new XorAggExpression(expresionListFinal);
		
		String gnuExpressionString = GNUPrologExpressionTransformer.transform(xorAggExpression).toArithmeticForm();
		assertEquals ("( Car.hp ) ## ( Car.color ) ## ( Car.value )", gnuExpressionString);
	}
}
