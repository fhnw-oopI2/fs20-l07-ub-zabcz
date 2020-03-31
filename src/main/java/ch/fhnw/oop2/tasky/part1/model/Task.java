package ch.fhnw.oop2.tasky.part1.model;

/**
 * Diese Klasse implementiert eine Task. Eine Task besteht aus der ID und den Daten.
 * 
 * Diese Klasse ist nicht veränderbar (immutable). Alle Felder sind öffentlich und
 * final implementiert.
 * 
 */
public class Task {
	
	public final long id;
	public final TaskData data;
	
	/**
	 * Erzeugt eine neue Task.
	 * 
	 * @param id  Die Identität (ID) der Task
	 * @param data  Die Daten der Task
	 */
	public Task(long id, TaskData data) {
	    if(data == null) {
	        throw new IllegalArgumentException("data must not be null!");
	    }
		this.id = id;
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "Task ["+id+"] " + data.toString();
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + data.hashCode();
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Task other = (Task) obj;
        if (!data.equals(other.data))
            return false;
        if (id != other.id)
            return false;
        return true;
    }
	
	
}
