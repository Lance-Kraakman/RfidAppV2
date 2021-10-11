package com.example.rfidappv2.mvvm;

import java.util.List;

public interface FirebaseCallbackListener <T>{
    void onSuccess(List<T> result);
    void onError(String error);

}
