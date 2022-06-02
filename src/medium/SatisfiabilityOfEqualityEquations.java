package medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SatisfiabilityOfEqualityEquations {

    public static void main(final String[] args) {
        String[] test1 = {"a==b","e==c","b==c","a!=e"};
        String[] test2 = {"c==c","f!=a","f==b","b==c"};
        String[] test3= {"a!=b","b!=c","c!=a"};
        String[] test4 = {"j!=h","i!=d","d!=f","b==h","i!=h","a==g","e==h"};

        final Solution sol = new SatisfiabilityOfEqualityEquations().new Solution();
//        boolean valid1 = sol.equationsPossible(test1);

//        System.out.println(valid1==false);
//        System.out.println(sol.equationsPossible(test2)==true);
//        System.out.println(sol.equationsPossible(test3)==true);
        System.out.println(sol.equationsPossible(test4)==true);
    }

    class Solution {
        public Solution() {};
        private Map<Character, Operand> heuristics;

        public boolean equationsPossible(String[] equations) {
            heuristics = new HashMap<>();
            for (String eq : equations) {
                boolean isValid = analyze(eq);
                if (!isValid) {
                    return isValid;
                }
            }
            return true;
        }

        private boolean analyze(String equation) {
            final char[] chars = equation.toCharArray();
            final char firstOperand = chars[0];
            final char secondOperand = chars[3];
            final char operator = chars[1];
            boolean isValid = true;
            isValid = process(firstOperand, secondOperand, operator) &&
                    process(secondOperand, firstOperand, operator);
            return isValid;
        }

        private boolean process(Character operand1, Character operand2, Character operator) {
            boolean isValid = true;
            if (operator == '!') {
                if (heuristics.containsKey(operand1)) {
                    Operand op1 = heuristics.get(operand1);
                    isValid = op1.isValid(operand2, operator);
                    op1.add(operand2, operator);
                    Operand op2;
                    if (heuristics.containsKey(operand2)) {
                        op2 = heuristics.get(operand2);
                        op1.add(op2.get('='), '!');
                    }
                } else {
                    Operand op = new Operand(operand1);
                    op.add(operand2, operator);
                    heuristics.put(operand1, op);
                }
                if (heuristics.containsKey(operand2)) {
                    Operand op2 = heuristics.get(operand2);
                    isValid = op2.isValid(operand1, operator);
                    op2.add(operand1, operator);
                    Operand op1;
                    if (heuristics.containsKey(operand1)) {
                        op1 = heuristics.get(operand1);
                        op2.add(op1.get('='), '!');
                    }
                }
            }
            if (operator == '=') {
                if (heuristics.containsKey(operand1)) {
                    Operand op1 = heuristics.get(operand1);
                    isValid = op1.isValid(operand2, operator);
                    op1.add(operand2, operator);
                    op1.add(operand1, operator);
                    Operand op2;
                    if (heuristics.containsKey(operand2)) {
                        op2 = heuristics.get(operand2);
                        op1.add(op2.get('='), '=');
                        op1.add(op2.get('!'), '!');
                    }
                    op2 = op1.clone(operand2);
                    heuristics.put(operand2, op2);
                } else {
                    Operand op = new Operand(operand1);
                    op.add(operand2, operator);
                    op.add(operand1, operator);
                    heuristics.put(operand1, op);
                }
                if (heuristics.containsKey(operand2)) {
                    Operand op2 = heuristics.get(operand2);
                    isValid = op2.isValid(operand1, operator);
                    op2.add(operand1, operator);
                    op2.add(operand2, operator);
                    Operand op1;
                    if (heuristics.containsKey(operand1)) {
                        op1 = heuristics.get(operand1);
                        op2.add(op1.get('='), '=');
                        op2.add(op1.get('!'), '!');
                    }
                    op1 = op2.clone(operand1);
                    heuristics.put(operand1, op1);
                }
            }
            return isValid;
        }

        class Operand {
            private Character operand;
            private Chain equalOperands;
            private Chain inequalOperands;
            public Operand(final Character operand) {
                this.operand = operand;
                this.equalOperands = new Chain();
                this.equalOperands.add(operand);
                this.inequalOperands = new Chain();
            }

            public Operand clone(Character character) {
                Operand operand = new Operand(character);
                operand.set(this.equalOperands, '=');
                operand.set(this.inequalOperands, '!');
                return operand;
            }

            public void add(char otherOperand, char operator) {
                if (operator == '!') {
                    inequalOperands.add(otherOperand);
                } else if (operator == '=') {
                    equalOperands.add(otherOperand);
                }
            }

            public void add(Chain otherOperand, char operator) {
                if (operator == '!') {
                    inequalOperands.add(otherOperand);
                } else if (operator == '=') {
                    equalOperands.add(otherOperand);
                }
            }

            public boolean isValid(char otherOperand, char operator) {
                if (operator == '!') {
                    return !equalOperands.contains(otherOperand);
                } else if (operator == '=') {
                    return !inequalOperands.contains(otherOperand);
                }
                return false;
            }

            public void set(Chain operands, Character operator) {
                if (operator == '=') {
                    this.equalOperands = operands;
                } else if (operator == '!') {
                    this.inequalOperands = operands;
                }
            }

            public Chain get(Character operator) {
                if (operator == '=') {
                    return this.equalOperands;
                } else if (operator == '!') {
                    return this.inequalOperands;
                }
                return new Chain();
            }
        }

        class Chain {
            private Set<Character> operands;
            public Chain() {
                this.operands = new HashSet();
            }
            public Chain(final Character c) {
                this.operands = new HashSet();
                this.add(c);
            }

            public void add(Chain chain) {
                operands.addAll(chain.get());
            }

            public Set<Character> get() {
                return this.operands;
            }
            public boolean contains(final Character c) {
                return this.operands.contains(c);
            }
            public void add(final Character c) {
                this.operands.add(c);
            }
        }
    }
}
