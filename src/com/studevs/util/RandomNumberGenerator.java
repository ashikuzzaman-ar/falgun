/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studevs.util;

/**
 *
 * @author ashik
 */
public class RandomNumberGenerator {

    private double initialNumber = 0;
    private double terminalNumber = Double.MAX_VALUE;
    private double generatedNumber;

    public RandomNumberGenerator() {

        try {

            this.initialNumber = 0;
            this.terminalNumber = Double.MAX_VALUE;
            this.generatedNumber = 0.0;
        } catch (Exception e) {

            System.err.println(e.toString());
        }
    }

    public RandomNumberGenerator(double terminalNumber) {

        this();

        try {

            this.terminalNumber = terminalNumber;
        } catch (Exception e) {

            System.err.println(e.toString());
        }
    }

    public RandomNumberGenerator(double initialNumber, double terminalNumber) {

        this(terminalNumber);

        try {

            this.initialNumber = initialNumber;
        } catch (Exception e) {

            System.err.println(e.toString());
        }
    }

    private void generate() {

        try {

            if (this.initialNumber < this.terminalNumber) {

                this.generatedNumber = (this.initialNumber + ((Math.random() * Double.MAX_VALUE) % (this.terminalNumber - this.initialNumber)));
            } else {

                System.err.println("Your initial value in greater than or equal to terminal value!");
                this.generatedNumber = -1;
            }
        } catch (Exception e) {

            System.err.println(e.toString());
            this.generatedNumber = -1;
        }
    }

    public void setInitialNumber(double initialNumber) {
        this.initialNumber = initialNumber;
    }

    public void setTerminalNumber(double terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public double getGeneratedNumber() {

        try {

            this.generate();
        } catch (Exception e) {

            System.err.println(e.toString());
            this.generatedNumber = -1;
        }

        return this.generatedNumber;
    }
}
