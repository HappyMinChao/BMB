package com.java8;

import sun.jvm.hotspot.oops.InstanceKlass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * CreateStream
 *
 * @author : minchao.du
 * @description :
 * @date : 2018/3/27
 */
public class CreateStream {
    
    public static void main(String[] args){
        createStreamFromCollection().forEach(System.out::println);  // 打印时和list中的顺序一致
        createStreamFromValue().forEach(System.out::println);
        createStreamFromArrays().forEach(System.out::println);
        createStreamFromFile();
    }

    private  static void createStreamFromFile(){
        Path path = Paths.get("/Users/mac-book/IdeaProjects/BMB/BMB_trans-core/src/main/java/com/java8/Dish.java");
        try {
            Stream<String> streamFromFile = Files.lines(path);
            streamFromFile.forEach(System.out::println);
        } catch (IOException e) {
            throw new  RuntimeException(e);
        }
    }
    
    private  static Stream<String> createStreamFromArrays(){
        return Arrays.stream(new String[] {"xxx", "2ww", "hello" , "alex"} );
    }
    
    private  static Stream<String> createStreamFromValue(){
        return Stream.of( "xxx", "2ww", "hello" , "alex" );
    }
    
    private static Stream<String> createStreamFromCollection(){
        List<String> list = Arrays.asList("xxx", "2ww", "hello" , "alex");
        return list.stream();
    }
}
