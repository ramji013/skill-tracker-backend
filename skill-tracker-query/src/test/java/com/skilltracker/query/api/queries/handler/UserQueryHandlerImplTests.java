package com.skilltracker.query.api.queries.handler;

import com.skilltracker.cqrs.core.domain.BaseEntity;
import com.skilltracker.user.models.UserEntity;
import com.skilltracker.query.api.queries.user.FindAllUsersQuery;
import com.skilltracker.query.api.queries.user.FindUserByIdQuery;
import com.skilltracker.query.api.queries.user.FindUserByNameQuery;
import com.skilltracker.query.api.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UserQueryHandlerImplTests {
  @InjectMocks UserQueryHandlerImpl queryHandler;

  @Mock private UserRepository repository;

  @Test
  void handleFindAllUsersQueryTest() {
    Iterable<UserEntity> mockList = new ArrayList<>();
    Mockito.when(this.repository.findAll()).thenReturn(mockList);

    var responseDto = this.queryHandler.handle(new FindAllUsersQuery());
    Assertions.assertNotNull(responseDto);
  }

  @Test
  void handleFindUserByNameQueryTest() {
    List<UserEntity> mockList = new ArrayList<>();
    Mockito.when(this.repository.findByName(ArgumentMatchers.any())).thenReturn(mockList);

    var responseDto = this.queryHandler.handle(new FindUserByNameQuery(""));
    Assertions.assertNotNull(responseDto);
  }

  @Test
  void handleFindUserByIdQueryTest() {
    UserEntity entity = Mockito.mock(UserEntity.class);
    List<BaseEntity> mockList = new ArrayList<>();
    Mockito.when(this.repository.findById(ArgumentMatchers.any()))
        .thenReturn(Optional.ofNullable(entity));

    var responseDto = this.queryHandler.handle(new FindUserByIdQuery(""));
    Assertions.assertNotNull(responseDto);
  }
}
