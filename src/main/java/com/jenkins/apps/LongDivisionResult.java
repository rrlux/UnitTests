package com.jenkins.apps;

import java.util.ArrayList;
import java.util.List;

class LongDivisionResult {
    private List<Integer> incompleteDividends = new ArrayList<Integer>();
    private List<Integer> incompleteProducts = new ArrayList<Integer>();
    private List<Integer> remainders = new ArrayList<Integer>();
    private List<Integer> incompleteQuotients = new ArrayList<Integer>();
    public void addIncompleteDividend(int incompleteDividend) {
        this.incompleteDividends.add(incompleteDividend);
    }
    public List<Integer> getIncompleteDividends() {
        return incompleteDividends;
    }
    public void addIncompleteProduct(int incompleteProduct) {
        this.incompleteProducts.add(incompleteProduct);
    }
    public List<Integer> getIncompleteProducts() {
        return incompleteProducts;
    }
    public void addRemainder(int remainder) {
        this.remainders.add(remainder);
    }
    public List<Integer> getRemainders() {
        return remainders;
    }
    public void addIncompleteQuotient(int incompleteQuotient) {
        this.incompleteQuotients.add(incompleteQuotient);
    }
    public List<Integer> getIncompleteQuotients() {
        return incompleteQuotients;
    }
}
