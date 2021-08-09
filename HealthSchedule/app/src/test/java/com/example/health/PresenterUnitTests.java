package com.example.health;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

import com.example.health.Classes.InputChecker;

public class PresenterUnitTests {
    private MainActivity mockMainActivity = mock(MainActivity.class);
    private Presenter presenter = new Presenter(mockMainActivity);
//    private InputChecker mockInputChecker = mock(InputChecker.class);

    @Before
    public void setUp() {
        when(mockMainActivity.getEmail()).thenReturn("mock@email.com");
        when(mockMainActivity.getPassword()).thenReturn("mockPassword");
    }

    @Test
    public void testAttemptLoginWrongEmailFormat() {
        when(InputChecker.checkEmail("mock@email.com")).thenReturn(false);
        when(InputChecker.checkPassword("mockPassword")).thenReturn(true);
        presenter.attemptLogin();

        try (MockedStatic<InputChecker> utilities = mockStatic(InputChecker.class)) {

        }
        verify(mockMainActivity, times(1)).showMessage("The input format is wrong!");
    }
}
