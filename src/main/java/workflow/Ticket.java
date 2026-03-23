package workflow;

public class Ticket {
    private static int nextId = 1;

    private final int id;
    private String title;
    private String description;
    private String priority;
    private Status status;

    public Ticket(String title, String description, String priority) {
        this.id          = nextId++;
        this.title       = title;
        this.description = description;
        this.priority    = priority;
        this.status      = Status.TO_DO;
    }

    public int    getId()          { return id; }
    public String getTitle()       { return title; }
    public String getDescription() { return description; }
    public String getPriority()    { return priority; }
    public Status getStatus()      { return status; }

    public void setTitle(String title)             { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setPriority(String priority)       { this.priority = priority; }
    public void setStatus(Status status)           { this.status = status; }

    @Override
    public String toString() {
        return String.format("  [#%d] %s  %s  %s  %s",
                id, title, priority, status, description);
    }
}