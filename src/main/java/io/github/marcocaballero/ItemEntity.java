package io.github.marcocaballero;

import javax.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
public class ItemEntity {

	private long id;
	private String name;

	protected ItemEntity() {
		this("Default_Name");
	}

	public ItemEntity(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	protected void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + "]";
	}
}
