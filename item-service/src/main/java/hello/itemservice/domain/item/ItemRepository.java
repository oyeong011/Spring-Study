package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); // HashMap is not thread-safe
    private static long sequence = 0L; // not thread-safe

    // save
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    // find one
    public Item findById(Long id) {
        return store.get(id);
    }

    // find all
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    // update
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    // clear store
    public void clearStore() {
        store.clear();
    }

}
