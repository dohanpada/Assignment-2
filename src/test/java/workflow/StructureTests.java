package workflow;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Modifier;

public class StructureTests {

    @Test
    void workflowBoardClassExists() {
        WorkflowBoard board = new WorkflowBoard();
        assertNotNull(board);
    }

    @Test
    public void workflowCommandIsInterface() {
        assertTrue(WorkflowCommand.class.isInterface());
    }

    @Test
    public void workflowCommandHasPerformCommand() throws Exception {
        assertNotNull(
                WorkflowCommand.class.getMethod("performCommand")
        );
    }

    @Test
    public void abstractWorkflowCommandIsAbstract() {
        assertTrue(
                Modifier.isAbstract(AbstractWorkflowCommand.class.getModifiers())
        );
    }

    @Test
    public void abstractWorkflowCommandImplementsInterface() {

        boolean found = false;

        for (Class<?> i : AbstractWorkflowCommand.class.getInterfaces()) {
            if (i.equals(WorkflowCommand.class)) {
                found = true;
            }
        }

        assertTrue(found);
    }

    @Test
    public void createCommandExtendsAbstract() {
        assertEquals(
                AbstractWorkflowCommand.class,
                CreateCommand.class.getSuperclass()
        );
    }

    @Test
    public void moveCommandExtendsAbstract() {
        assertEquals(
                AbstractWorkflowCommand.class,
                MoveCommand.class.getSuperclass()
        );
    }

    @Test
    public void alterCommandExtendsAbstract() {
        assertEquals(
                AbstractWorkflowCommand.class,
                AlterCommand.class.getSuperclass()
        );
    }

}