package org.example.expert.domain.todo.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.todo.repository.QueryDslTodoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.example.expert.domain.todo.entity.QTodo.todo;
import static org.example.expert.domain.user.entity.QUser.user;


@Repository
@RequiredArgsConstructor
public class TodoRepositoryImpl implements QueryDslTodoRepository {

    private final JPAQueryFactory q;

    public Optional<Todo> findByIdWithUser(Long todoId){
        Todo result =  q
                .select(todo)
                .from(todo)
                .leftJoin(todo.user, user).fetchJoin()
                .where(todo.id.eq(todoId))
                .fetchOne();

        return Optional.ofNullable(result);

    }

}
