package com.bantanger.dao;

import com.bantanger.entity.GroupUser;
import com.bantanger.entity.QUserGroup;
import com.bantanger.entity.Task;
import com.bantanger.entity.UserGroup;
import com.bantanger.repository.UserGroupRepository;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.bantanger.entity.QGroupUser.groupUser;
import static com.bantanger.entity.QTask.task;
import static com.bantanger.entity.QUserGroup.userGroup;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/27
 */
@SpringBootTest
public class UserGroupTest {

    @Resource
    private EntityManager em;
    @Resource
    private EntityManagerFactory emf;
    @Resource
    private UserGroupRepository repository;
    private JPQLQueryFactory queryFactory = null;

    @Test
    public void populateDatabase() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Stream.of("Group 1", "Group 2", "Group 3")
                .forEach(g -> {
                    UserGroup userGroup = new UserGroup();
                    userGroup.setName(g);
                    em.persist(userGroup);
                    IntStream.range(0, 10)
                            .forEach(u -> {
                                GroupUser groupUser = new GroupUser();
                                groupUser.setLogin("User2" + u);
                                groupUser.getUserGroups().add(userGroup);
                                em.persist(groupUser);
                                userGroup.getGroupUsers().add(groupUser);
                                IntStream.range(0, 10000)
                                        .forEach(t -> {
                                            Task task = new Task();
                                            task.setDescription(groupUser.getLogin() + " task #" + t);
                                            task.setUser(groupUser);
                                            em.persist(task);
                                        });
                            });
                    em.merge(userGroup);
                });

        em.getTransaction().commit();
        em.close();
    }

    @BeforeEach
    public void setup() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        queryFactory = new JPAQueryFactory(em);
//
//        createUserGroup("Group 1");
//        createUserGroup("Group 4");
    }

    @Test
    public void init() {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
        for (int i = 0; i < 3; i++) {
            repository.save(new UserGroup("bantanger-" + i, new HashSet<>()));
        }
    }

    private void createUserGroup(String name) {
        UserGroup entity = new UserGroup();
        entity.setName(name);
        repository.save(entity);
    }

    @Test
    public void givenJpaCriteria_getAllUserGroups_test() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserGroup> cr = cb.createQuery(UserGroup.class);
        Root<UserGroup> root = cr.from(UserGroup.class);
        CriteriaQuery<UserGroup> select = cr.select(root);

        TypedQuery<UserGroup> query = em.createQuery(select);
        List<UserGroup> results = query.getResultList();
        Assertions.assertEquals(3, results.size());
    }

    @Test
    public void givenQuerydsl_getAllUserGroups_test() {
        long count = queryFactory.selectFrom(userGroup).fetchCount();
        Assertions.assertEquals(3, count);
    }

    @Test
    public void givenQuerydsl_getUserGroups_AggregatedData_test() {
        List<Tuple> result = queryFactory.select(userGroup)
                .select(userGroup.name, userGroup.id.countDistinct())
                .from(userGroup)
                .where(userGroup.name.in("Group 1", "Group 2")
                        .or(userGroup.name.in("Group 4", "Group 5")))
                .orderBy(userGroup.name.desc())
                .groupBy(userGroup.name)
                .fetch();

        System.out.println(result);

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Group 2", result.get(0).get(userGroup.name));
        Assertions.assertEquals(1L, result.get(0).get(userGroup.id.countDistinct()));
        Assertions.assertEquals("Group 1", result.get(1).get(userGroup.name));
        Assertions.assertEquals(1L, result.get(1).get(userGroup.id.countDistinct()));
    }

    @Test
    public void test() {
        Stream.of("Group 1", "Group 2", "Group 3")
            .forEach(g -> {
                UserGroup userGroup = new UserGroup();
                userGroup.setName(g);
                em.persist(userGroup);
                IntStream.range(0, 10)
                    .forEach(u -> {
                        GroupUser groupUser = new GroupUser();
                        groupUser.setLogin("User2" + u);
                        groupUser.getUserGroups().add(userGroup);
                        em.persist(groupUser);
                        userGroup.getGroupUsers().add(groupUser);
                        IntStream.range(0, 10000)
                            .forEach(t -> {
                                Task task = new Task();
                                task.setDescription(groupUser.getLogin() + " task #" + t);
                                task.setUser(groupUser);
                                em.persist(task);
                            });
                    });
                em.merge(userGroup);
            });

        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void givenQuerydsl_getUserGroupsWithJoin_test() {
        List<UserGroup> result = queryFactory.selectFrom(userGroup)
                .leftJoin(userGroup.groupUsers, groupUser)
                .leftJoin(groupUser.tasks, task)
                .fetch();
        assertUserGroups(result);
    }

    private void assertUserGroups(List<UserGroup> userGroups) {
        assertEquals(3, userGroups.size());
        for (UserGroup group : userGroups) {
            assertEquals(10, group.getGroupUsers().size());
            for (GroupUser user : group.getGroupUsers()) {
                assertEquals(10000, user.getTasks().size());
            }
        }
    }

}
