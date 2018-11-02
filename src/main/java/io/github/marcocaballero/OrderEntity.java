package io.github.marcocaballero;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
public class OrderEntity {

	private long id;
	private String title;
	private List<ItemEntity> items;

	public OrderEntity() {
		this("Default title");
	}

	public OrderEntity(String title) {
		this(title, new ArrayList<ItemEntity>());
	}

	public OrderEntity(String title, List<ItemEntity> items) {
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

	@OneToMany(cascade = CascadeType.ALL)
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

	protected void setItemsCount(int items) {
	}

	public void removeItemByName(String name) {
		Optional<ItemEntity> itemToRemove = getItemByName(name);
		if (itemToRemove.isPresent()) {
			items.remove(itemToRemove.get());
		}
	}

	public void updateItemName(String name) {
		Optional<ItemEntity> itemToUpdate = getItemByName(name);
		if (itemToUpdate.isPresent()) {
			itemToUpdate.get().setName(name);
		}
	}

	private Optional<ItemEntity> getItemByName(String name) {
		return items.stream()
							.filter(item -> item.getName() == name)
							.findFirst();
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", title=" + title + ", title=" + getItemsCount() + "]";
	}

}
