package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
@Transactional
@RequiredArgsConstructor
public class initDb implements ApplicationRunner {
    private final EntityManager em;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        dbInit1();
        dbInit2();
    }

    public void dbInit1() {
        Member member = createMember("uesrA", "서울", "11", "22");
        em.persist(member);

        Book book1 = createBook("JPA1 BOOK", 10000);
        em.persist(book1);
        Book book2 = createBook("JPA2 BOOK", 20000);
        em.persist(book2);

        OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
        OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

        Delivery delivery = createDelivery(member);
        Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
        em.persist(order);
    }

    public void dbInit2() {
        Member member = createMember("userB", "포천", "23", "245");
        em.persist(member);

        Book book1 = createBook("SPRING1 BOOK", 10000);
        em.persist(book1);
        Book book2 = createBook("SPRING2 BOOK", 20000);
        em.persist(book2);

        OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
        OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

        Delivery delivery = createDelivery(member);
        Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
        em.persist(order);
    }

    private Delivery createDelivery(Member member) {
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        return delivery;
    }

    private Book createBook(String s, int i) {
        Book book1 = new Book();
        book1.setName(s);
        book1.setPrice(i);
        book1.setStockQuantity(100);

        return book1;
    }

    private Member createMember(String username, String city, String street, String zipcode) {
        Member member = new Member();
        member.setName(username);
        member.setAddress(new Address(city, street, zipcode));
        em.persist(member);

        return member;
    }
}
