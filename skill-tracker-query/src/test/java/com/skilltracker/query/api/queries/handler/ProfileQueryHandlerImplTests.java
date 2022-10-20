package com.skilltracker.query.api.queries.handler;

import com.skilltracker.cqrs.core.domain.BaseEntity;
import com.skilltracker.profile.models.EmployeeEntity;
import com.skilltracker.query.api.queries.profile.FindAllProfilesQuery;
import com.skilltracker.query.api.queries.profile.FindProfileByIdQuery;
import com.skilltracker.query.api.queries.profile.FindProfileByNameQuery;
import com.skilltracker.query.api.queries.profile.FindProfileWithSkillQuery;
import com.skilltracker.query.api.repositories.ProfileRepository;
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
class ProfileQueryHandlerImplTests {
  @InjectMocks ProfileQueryHandlerImpl queryHandler;

  @Mock private ProfileRepository repository;

  @Test
  void handleFindAllProfilesQueryTest() {
    Iterable<EmployeeEntity> mockList = new ArrayList<>();
    Mockito.when(this.repository.findAll()).thenReturn(mockList);

    var responseDto = this.queryHandler.handle(new FindAllProfilesQuery());
    Assertions.assertNotNull(responseDto);
  }

  @Test
  void handleFindProfileByIdQueryTest() {
    List<BaseEntity> mockList = new ArrayList<>();
    Mockito.when(this.repository.findById(ArgumentMatchers.any()))
        .thenReturn(Optional.of(new EmployeeEntity()));

    var responseDto = this.queryHandler.handle(new FindProfileByIdQuery(""));
    Assertions.assertNotNull(responseDto);
  }

  @Test
  void handleFindProfileWithSkillQueryExceptionTest() {
    var findProfileWithSkillQuery = new FindProfileWithSkillQuery("");
    var thrown =
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> this.queryHandler.handle(findProfileWithSkillQuery),
            "Expected handle() to throw, but it didn't");

    Assertions.assertFalse(thrown.getMessage().contains("No suitable skill found!"));
  }
}
