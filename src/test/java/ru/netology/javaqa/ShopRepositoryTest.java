package ru.netology.javaqa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ShopRepositoryTest {
    Product item1 = new Product(11, "хлеб", 40);
    Product item2 = new Product(222, "булка", 30);
    Product item3 = new Product(3, "картошка", 20);

    ShopRepository repo = new ShopRepository();

    @Test
    public void shouldRemoveByIdDoesNotExist() {
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(1);
        });
    }

    @Test
    public void shouldRemoveById() {
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);

        repo.removeById(3);

        Product[] expected = {item1, item2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddByRepeatingId() {
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);

        Product item4 = new Product(3, "белый картофель", 50);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(item4);
        });
    }

    @Test
    public void shouldAddById() {
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);

        Product item5 = new Product(44, "багет с чесноком", 45);
        repo.add(item5);

        Product[] expected = {item1, item2, item3, item5};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }
}
