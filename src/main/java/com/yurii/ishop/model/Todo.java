package com.yurii.ishop.model;

import com.yurii.ishop.entity.TodoEntity;
import lombok.Data;

@Data
public class Todo {

    private Long id;
    private String title;
    private Boolean completed;

    public static Todo toModel (TodoEntity entity){
        Todo model = new Todo();
        model.setId(entity.getId());
        model.setCompleted(entity.getCompleted());
        model.setTitle(entity.getTitle());
        return model;
    }

}
