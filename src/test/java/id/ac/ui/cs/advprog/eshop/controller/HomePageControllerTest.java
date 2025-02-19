package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HomePageControllerTest {

    @Test
    void testHome() {
        HomePageController controller = new HomePageController();
        Model model = mock(Model.class);

        String viewName = controller.home(model);

        assertEquals("HomePage", viewName);
        verify(model).addAttribute("message", "Welcome to the Home Page!");
    }
}