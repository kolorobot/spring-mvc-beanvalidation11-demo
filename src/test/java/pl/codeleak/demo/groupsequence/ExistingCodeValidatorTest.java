package pl.codeleak.demo.groupsequence;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ExistingCodeValidatorTest {

    @Mock
    private TokenRetrievalService tokenRetrievalService;

    private ExistingCodeValidator validator;

    @Before
    public void setUp() throws Exception {
        validator = new ExistingCodeValidator(tokenRetrievalService);
    }

    @Test
    public void isValid() throws TokenNotFoundException {
        // arrange
        Code code = new Code();
        code.setCode("1");

        Mockito.when(tokenRetrievalService.getToken("1")).thenReturn("1");
        // act
        boolean result = validator.isValid(code, null);
        // assert
        assertThat(result).isTrue();
        Mockito.verify(tokenRetrievalService).getToken("1");
    }

    @Test
    public void isNotValidOnException() throws TokenNotFoundException {
        // arrange
        Code code = new Code();
        code.setCode("1");
        Mockito.when(tokenRetrievalService.getToken("1")).thenThrow(new TokenNotFoundException());
        // act
        boolean result = validator.isValid(code, null);
        // assert
        assertThat(result).isFalse();
        Mockito.verify(tokenRetrievalService).getToken("1");
    }
}