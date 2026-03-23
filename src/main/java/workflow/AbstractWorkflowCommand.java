package workflow;

public abstract class AbstractWorkflowCommand implements WorkflowCommand {

    protected WorkflowBoard board;

    public AbstractWorkflowCommand(WorkflowBoard board) {
        this.board = board;
    }

    @Override
    public abstract void performCommand();
}