package com.grpc.example.grpcDemo.grpc;

import com.grpc.example.grpcDemo.GetGeoInfoResponse;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class GrpcDaoService {

    public static Map<Integer,GetGeoInfoResponse> MAP = new HashMap<>();
    static {
        MAP.put(84, GetGeoInfoResponse.newBuilder()
                .setGeoCode(84)
                .setName("VN")
                .build());
        MAP.put(234, GetGeoInfoResponse.newBuilder()
                .setGeoCode(234)
                .setName("NG")
                .build());
        MAP.put(49, GetGeoInfoResponse.newBuilder()
                .setGeoCode(84)
                .setName("DF")
                .build());
        MAP.put(1, GetGeoInfoResponse.newBuilder()
                .setGeoCode(84)
                .setName("ON")
                .build());
    }

    public List<GetGeoInfoResponse> getAll() {
        return new ArrayList<>(MAP.values());
    }

    public GetGeoInfoResponse getById(Integer id) {
        return MAP.getOrDefault(id, GetGeoInfoResponse.newBuilder().build());
    }


}