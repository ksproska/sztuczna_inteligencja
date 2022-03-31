import java.util.ArrayList;

public class CSP_ForwardCheckingSolver <P, D extends P, T extends CSP_Problem<P, D>, S extends CSP_PartialSolution<P, D>> implements CSP_Solver<P, D, T, S> {
    private final T cspProblem;
    public CSP_ForwardCheckingSolver(T cspProblem) {
        this.cspProblem = cspProblem;
    }

    public ArrayList<S> getResults() {
        ArrayList<S> accumulator = new ArrayList<>();
        var initialPartialSolution = cspProblem.getInitialPartialSolution();
        getResultsRecursive((S) initialPartialSolution, accumulator);
        return accumulator;
    }

    private void getResultsRecursive(S cspPartialSolution,
                                     ArrayList<S> accumulator) {
        if(cspPartialSolution.isSatisfied()) {
            accumulator.add(cspPartialSolution);
            return;
        }
        var nextVariable = cspPartialSolution.getNextVariable();
        if (nextVariable == null) return;

        var searchedDomain = new ArrayList<>(nextVariable.getVariableDomain());
        for (int i = 0; i < searchedDomain.size(); i++) {
            var domainItem = searchedDomain.get(i);
            var solutionCopy = cspPartialSolution;
            if(i != searchedDomain.size() - 1) {
                solutionCopy = (S) cspPartialSolution.deepClone();
            }
            var changedVariableInx = nextVariable.variableIndex;
            solutionCopy.setNewValueAtIndexOf(domainItem, changedVariableInx);
            boolean areValuesCorrect = solutionCopy.updateVariables(changedVariableInx);
            if(areValuesCorrect) {
                getResultsRecursive(solutionCopy, accumulator);
            }
        }
    }
}
