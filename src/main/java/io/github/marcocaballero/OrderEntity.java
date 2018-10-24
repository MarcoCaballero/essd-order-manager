package io.github.marcocaballero;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
public class OrderEntity {

	private long id;
	private String title;
	private List<ItemEntity> items;

	protected OrderEntity() {
		this("Default title");
	}

	public OrderEntity(String title) {
		this(title, new ArrayList<ItemEntity>());
	}

	public OrderEntity(String title, List<ItemEntity> items)  {
		this.title = title;
		this.items = items;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@OneToMany(cascade=CascadeType.ALL)
	public List<ItemEntity> getItems() {
		return items;
	}

	public void setItems(List<ItemEntity> items) {
		this.items = items;
	}

	@Column(name = "itemsCount")
	public int getItemsCount() {
		return items.size();
	}

	protected void setItemsCount(int items) {}

	@Override
	public String toString() {
		return "Order [id=" + id + ", title=" + title + ", title=" + getItemsCount() + "]";
	}

}
