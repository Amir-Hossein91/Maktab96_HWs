package question1;

public class Rational {
    private int numerator; //Soorat
    private int denominator; //Makhraj
    public Rational(){
        numerator =0;
        denominator =1;
    }
    public Rational(int numerator, int denominator){
        if(denominator == 0)
            throw new IllegalArgumentException("Denominator can not be zero!");
        if(denominator<0){
            this.numerator = - numerator;
            this.denominator = - denominator;
        }
        else {
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }

    public void setSimplifiedValue(int numerator, int denominator){
        int maxMutualDivisor = 1;
        if(denominator<0){
            numerator = - numerator;
            denominator = - denominator;
        }
        for (int i =2; i<=Math.min(Math.abs(numerator),Math.abs(denominator)); i++){
            if (Math.abs(numerator)%i == 0 && Math.abs(denominator)%i == 0)
                maxMutualDivisor = i;
        }
        this.numerator = numerator/maxMutualDivisor;
        this.denominator = denominator/maxMutualDivisor;

    }

    public Rational add (Rational number){
        Rational result = new Rational();
        result.setSimplifiedValue(this.numerator * number.denominator + this.denominator * number.numerator, this.denominator * number.denominator);
        return result;
    }
    public Rational sub (Rational number){
        Rational result = new Rational();
        result.setSimplifiedValue(this.numerator * number.denominator - this.denominator * number.numerator, this.denominator * number.denominator);
        return result;
    }
    public Rational mul (Rational number){
        Rational result = new Rational();
        result.setSimplifiedValue(this.numerator * number.numerator, this.denominator * number.denominator);
        return result;
    }
    public Rational div (Rational number){
        if(number.getNumerator()==0)
            throw new IllegalArgumentException("Division by zero!");
        Rational result = new Rational();
        result.setSimplifiedValue(this.numerator * number.denominator, this.denominator * number.numerator);
        return result;
    }
    public double toFloatingPoint(){
        return (float)this.numerator/(float)this.denominator;
    }

    public String toString(){
        String result ="";
        if(this.numerator == 0)
            result += "0";
        else
            result += numerator + "/" + denominator;
        return result;
    }

    public String toComplexNumber(){
        String result = "";
        int complete = this.numerator / this.denominator;
        Rational remainedRational = new Rational();
        remainedRational.setSimplifiedValue(this.numerator-complete * this.denominator , this.denominator);
        if(remainedRational.getNumerator()<0)
            result += complete + " + (" + remainedRational + ")";
        else
            result += complete + " + " + remainedRational;
        return result;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }
}
