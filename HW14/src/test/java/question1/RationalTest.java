package question1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RationalTest {
    Rational test1 = new Rational();
    Rational test2 = new Rational();

    @Test
    void noArgConstructorMustReturnZeroOnOne(){
        assertTrue(test1.getNumerator()==0 && test1.getDenominator() == 1);
    }
    @Test
    void fourOnEightConstructorMustReturnFourOnEight(){
        test1 = new Rational(4,8);
        assertTrue(test1.getNumerator()==4 && test1.getDenominator() == 8);
    }
    @Test
    void denominatorCanNotBeZero(){
        test1.setNumerator(4);
        test1.setDenominator(0);
        assertThrows(IllegalArgumentException.class,() -> new Rational(test1.getNumerator(),test1.getDenominator()));
    }
    @Test
    void fiveOnThreeMustBeSimplifiedToFiveOnThree(){
        test1.setNumerator(5);
        test1.setDenominator(3);
        test1.setSimplifiedValue(test1.getNumerator(), test1.getDenominator());
        assertTrue(test1.getNumerator()==5 && test1.getDenominator() == 3);
    }
    @Test
    void fourOnEightMustBeSimplifiedToOneOnTwo(){
        test1.setNumerator(4);
        test1.setDenominator(8);
        test1.setSimplifiedValue(test1.getNumerator(), test1.getDenominator());
        assertTrue(test1.getNumerator()==1 && test1.getDenominator() == 2);
    }
    @Test
    void nineOnThreeMustBeSimplifiedToThreeOnOne(){
        test1.setNumerator(9);
        test1.setDenominator(3);
        test1.setSimplifiedValue(test1.getNumerator(), test1.getDenominator());
        assertTrue(test1.getNumerator()==3 && test1.getDenominator() == 1);
    }
    @Test
    void zeroOnFourMustBeSimplifiedToZeroOnOne(){
        test1.setNumerator(0);
        test1.setDenominator(4);
        test1.setSimplifiedValue(test1.getNumerator(), test1.getDenominator());
        assertTrue(test1.getNumerator()==0 && test1.getDenominator() == 4);
    }
    @Test
    void oneOnTwoPlusOneOnTwoEqualsOneOnOne(){
        test1 = new Rational(1,2);
        test2 = new Rational(1,2);
        Rational test3 = test1.add(test2);
        assertTrue(test3.getNumerator()==1 && test3.getDenominator()==1);
    }
    @Test
    void nineOnSevenPlusFourOnFiveEqualsSeventyThreeOnThirtyFive(){
        test1 = new Rational(9,7);
        test2 = new Rational(4,5);
        Rational test3 = test1.add(test2);
        assertTrue(test3.getNumerator()==73 && test3.getDenominator()==35);
    }
    @Test
    void zeroPlusFourOnSixEqualsTwoOnThree(){
        test1 = new Rational(0,1);
        test2 = new Rational(4,6);
        Rational test3 = test1.add(test2);
        assertTrue(test3.getNumerator()==2 && test3.getDenominator()==3);
    }
    @Test
    void eightOnThirtyTwoPlusZeroEqualsOneOnFour(){
        test1 = new Rational(8,32);
        test2 = new Rational(0,1);
        Rational test3 = test1.add(test2);
        assertTrue(test3.getNumerator()==1 && test3.getDenominator()==4);
    }
    @Test
    void oneOnFourPlusMinusOneOnTwoEqualsMinusOneOnFour(){
        test1 = new Rational(1,4);
        test2 = new Rational(-1,2);
        Rational test3 = test1.add(test2);
        assertTrue(test3.getNumerator()==-1 && test3.getDenominator()==4);
    }
    @Test
    void oneOnTwoPlusMinusOneOnTwoEqualsZeroOnOne(){
        test1 = new Rational(1,2);
        test2 = new Rational(-1,2);
        Rational test3 = test1.add(test2);
        assertTrue(test3.getNumerator()==0 && test3.getDenominator()==4);
    }
    @Test
    void oneOnTwoSubtractOneOnTwoEqualsZeroOnOne(){
        test1 = new Rational(1,2);
        test2 = new Rational(1,2);
        Rational test3 = test1.sub(test2);
        assertTrue(test3.getNumerator()==0 && test3.getDenominator()==4);
    }
    @Test
    void nineOnSevenSubtractFourOnFiveEqualsSeventeenOnThirtyFive(){
        test1 = new Rational(9,7);
        test2 = new Rational(4,5);
        Rational test3 = test1.sub(test2);
        assertTrue(test3.getNumerator()==17 && test3.getDenominator()==35);
    }
    @Test
    void zeroSubtractFourOnSixEqualsMinusTwoOnThree(){
        test1 = new Rational(0,1);
        test2 = new Rational(4,6);
        Rational test3 = test1.sub(test2);
        assertTrue(test3.getNumerator()==-2 && test3.getDenominator()==3);
    }
    @Test
    void eightOnThirtyTwoSubtractZeroEqualsOneOnFour(){
        test1 = new Rational(8,32);
        test2 = new Rational(0,1);
        Rational test3 = test1.sub(test2);
        assertTrue(test3.getNumerator()==1 && test3.getDenominator()==4);
    }
    @Test
    void oneOnTwoSubtractMinusOneOnTwoEqualsOneOnOne(){
        test1 = new Rational(1,2);
        test2 = new Rational(-1,2);
        Rational test3 = test1.sub(test2);
        assertTrue(test3.getNumerator()==1 && test3.getDenominator()==1);
    }
    @Test
    void fourOnSevenMultipliedByMinusThreeOnSixEqualsMinusTwoOnSeven(){
        test1 = new Rational(4,7);
        test2 = new Rational(-3,6);
        Rational test3 = test1.mul(test2);
        assertTrue(test3.getNumerator()==-2 && test3.getDenominator()==7);
    }
    @Test
    void oneOnTwoMultipliedByOneOnTwoEqualsOneOnFour(){
        test1 = new Rational(1,2);
        test2 = new Rational(1,2);
        Rational test3 = test1.mul(test2);
        assertTrue(test3.getNumerator()==1 && test3.getDenominator()==4);
    }
    @Test
    void zeroMultipliedByFourOnMinusFiveEqualsZeroOnFive(){
        test1 = new Rational(0,1);
        test2 = new Rational(4,-5);
        Rational test3 = test1.mul(test2);
        assertTrue(test3.getNumerator()==0 && test3.getDenominator()==5);
    }
    @Test
    void minusThreeOnSevenMultipliedByZeroEqualsZeroOnSeven(){
        test1 = new Rational(-3,7);
        test2 = new Rational(0,1);
        Rational test3 = test1.mul(test2);
        assertTrue(test3.getNumerator()==0 && test3.getDenominator()==7);
    }
    @Test
    void fourOnFiveMultipliedByFiveOnFourEqualsOne(){
        test1 = new Rational(4,5);
        test2 = new Rational(5,4);
        Rational test3 = test1.mul(test2);
        assertTrue(test3.getNumerator()==1 && test3.getDenominator()==1);
    }
    @Test
    void minusThreeOnSevenMultipliedByTwentyOneOnMinusSevenEqualsNineOnSeven(){
        test1 = new Rational(-3,7);
        test2 = new Rational(21,-7);
        Rational test3 = test1.mul(test2);
        assertTrue(test3.getNumerator()==9 && test3.getDenominator()==7);
    }
    @Test
    void fourOnEightDividedByFourOnEightEqualsOne(){
        test1 = new Rational(4,8);
        test2 = new Rational(4,8);
        Rational test3 = test1.div(test2);
        assertTrue(test3.getNumerator()==1 && test3.getDenominator()==1);
    }
    @Test
    void minusSevenOnFourDividedByMinusFourOnSevenEqualsFortyNineOnSixteen(){
        test1 = new Rational(-7,4);
        test2 = new Rational(-4,7);
        Rational test3 = test1.div(test2);
        assertTrue(test3.getNumerator()==49 && test3.getDenominator()==16);
    }
    @Test
    void fiveOnEigtDividedByZeroOnFiveThrowsException(){
        test1 = new Rational(5,8);
        test2 = new Rational(0,5);
        assertThrows(IllegalArgumentException.class,() -> test1.div(test2));
    }
    @Test
    void fourOnSevenDividedBySixOnMinusThreeEqualsMinusTwoOnSeven(){
        test1 = new Rational(4,7);
        test2 = new Rational(6,-3);
        Rational test3 = test1.div(test2);
        assertTrue(test3.getNumerator()==-2 && test3.getDenominator()==7);
    }
    @Test
    void fourOnSevenDividedByMinusSixOnThreeEqualsMinusTwoOnSeven(){
        test1 = new Rational(4,7);
        test2 = new Rational(-6,3);
        Rational test3 = test1.div(test2);
        assertTrue(test3.getNumerator()==-2 && test3.getDenominator()==7);
    }
    @Test
    void zeroOnFourDividedByTwentyThreeOnTwentyOneEqualsZeroOnNinetyTwo(){
        test1 = new Rational(0,4);
        test2 = new Rational(23,21);
        Rational test3 = test1.div(test2);
        assertTrue(test3.getNumerator()==0 && test3.getDenominator()==92);
    }
    @Test
    void zeroOnFourDividedByMinusTwentyThreeOnTwentyOneEqualsZeroOnNinetyTwo(){
        test1 = new Rational(0,4);
        test2 = new Rational(-23,21);
        Rational test3 = test1.div(test2);
        assertTrue(test3.getNumerator()==0 && test3.getDenominator()==92);
    }
    @Test
    void fourOnEightEqualsZeroPointFive(){
        test1 = new Rational(4,8);
        assertEquals(0.5, test1.toFloatingPoint());
    }
    @Test
    void MinusThreeOnTwelveEqualsMinusPointTwentyFive(){
        test1 = new Rational(-3,12);
        assertEquals(-0.25, test1.toFloatingPoint());
    }
    @Test
    void twentyFiveOnFourEqualsSixPointTwentyFive(){
        test1 = new Rational(25,4);
        assertEquals(6.25, test1.toFloatingPoint());
    }
    @Test
    void ThirtyNineOnMinusThirteenEqualsMinusThreePointZero(){
        test1 = new Rational(39,-13);
        assertEquals(-3.0, test1.toFloatingPoint());
    }
    @Test
    void nineOnFourEqualsTwoPlusOneOnFour(){
        test1 = new Rational(9,4);
        assertEquals("2 + 1/4", test1.toComplexNumber());
    }
    @Test
    void EighteenOnFourEqualsFourPlusOneOnTwo(){
        test1 = new Rational(18,4);
        assertEquals("4 + 1/2", test1.toComplexNumber());
    }@Test
    void fourOnFiveEqualsZeroPlusFourOnFive(){
        test1 = new Rational(4,5);
        assertEquals("0 + 4/5", test1.toComplexNumber());
    }
    @Test
    void minusEighteenOnFourEqualsMinusFourPlusMinusOneOnTwo(){
        test1 = new Rational(-18,4);
        assertEquals("-4 + (-1/2)", test1.toComplexNumber());
    }
    @Test
    void eighteenOnMinusFourEqualsMinusFourPlusMinusOneOnTwo(){
        test1 = new Rational(18,-4);
        assertEquals("-4 + (-1/2)", test1.toComplexNumber());
    }
    @Test
    void ZeroOnFiveEqualsZeroPlusZero(){
        test1 = new Rational(0,5);
        assertEquals("0 + 0", test1.toComplexNumber());
    }
    @Test
    void twentyThreeOnElevenEqualsTwoPlusOneOnEleven(){
        test1 = new Rational(23,11);
        assertEquals("2 + 1/11", test1.toComplexNumber());
    }

}