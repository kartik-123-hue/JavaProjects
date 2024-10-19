package org.Demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;



@RunWith(MockitoJUnitRunner.class)
class CommandLineInterfaceTest {
    @InjectMocks
    CommandLineInterface commandLineInterface;
    @Test
    void testCreatingTask(){
        String input="add task1\nexit";
        InputStream inputStream=new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        CommandLineInterface.main(new String[]{});
    }
    @Test
    void testUpdateTask(){
        String command="update 7 testing for junit\nexit";
        InputStream inputStream=new ByteArrayInputStream(command.getBytes());
        System.setIn(inputStream);
        CommandLineInterface.main(new String[]{});
    }
    @Test
    void testDeleteTask(){
        String command="delete 7\nexit";
        InputStream inputStream=new ByteArrayInputStream(command.getBytes());
        System.setIn(inputStream);
        CommandLineInterface.main(new String[]{});
    }

    @Test
    void testListAllTask(){
        String command="list\nexit";
        InputStream inputStream=new ByteArrayInputStream(command.getBytes());
        System.setIn(inputStream);
        CommandLineInterface.main(new String[]{});
    }

    @Test
    void testTaskStatus(){
        String command="status update 2 completed\nexit";
        InputStream inputStream=new ByteArrayInputStream(command.getBytes());
        System.setIn(inputStream);
        CommandLineInterface.main(new String[]{});
    }

    @Test
    void testAllInProgressTask(){
        String command="status inprogress\nexit";
        InputStream inputStream=new ByteArrayInputStream(command.getBytes());
        System.setIn(inputStream);
        CommandLineInterface.main(new String[]{});
    }

    @Test
    void testAllCompletedTask(){
        String command="status completed\nexit";
        InputStream inputStream=new ByteArrayInputStream(command.getBytes());
        System.setIn(inputStream);
        CommandLineInterface.main(new String[]{});
    }

    @Test
    void testAlltodoTask(){
        String command="status todo\nexit";
        InputStream inputStream=new ByteArrayInputStream(command.getBytes());
        System.setIn(inputStream);
        CommandLineInterface.main(new String[]{});
    }
}