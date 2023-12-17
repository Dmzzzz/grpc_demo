package com.grpc.example.grpcDemo.grpc;

import com.google.common.collect.Lists;
import com.google.protobuf.Empty;
import com.grpc.example.grpcDemo.GeoServiceGrpc;
import com.grpc.example.grpcDemo.GetGeoInfoResponse;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;
import java.io.IOException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class GeoGrpcServiceTest {


    @Rule
    public GrpcCleanupRule grpcCleanupRule = new GrpcCleanupRule();

    private GrpcDaoService grpcDaoService = Mockito.mock(GrpcDaoService.class);

    @MockBean
    private MyGrpcAspect myGrpcAspect;


    @Test
    void grpcTest() throws IOException {

        Mockito.when(grpcDaoService.getAll()).thenReturn(
                Lists.newArrayList(GrpcDaoService.MAP.values()));

        String serverName = InProcessServerBuilder.generateName();

        grpcCleanupRule.register(InProcessServerBuilder
        .forName(serverName).directExecutor().addService(new GeoGrpcService(
                grpcDaoService
                )).build()
        .start());

        GeoServiceGrpc.GeoServiceBlockingStub blockingStub =
                GeoServiceGrpc.newBlockingStub(grpcCleanupRule.register(
                        InProcessChannelBuilder.forName(serverName).directExecutor()
                        .build()
                ));

        Iterator<GetGeoInfoResponse> iterator = blockingStub.getAllGeo(Empty.newBuilder().build());

        Assertions.assertTrue(iterator.hasNext());

        GetGeoInfoResponse next = iterator.next();

        Assertions.assertEquals(next.getGeoCode(),84);

        GetGeoInfoResponse next1 = iterator.next();
        ArrayList<GetGeoInfoResponse> list = Lists.newArrayList(iterator);

        Mockito.verify(myGrpcAspect).allGrpc(Mockito.any());

        String a = "a";

    }


}