package com.grpc.example.grpcDemo.grpc;

import com.google.protobuf.Empty;
import com.grpc.example.grpcDemo.GeoServiceGrpc;
import com.grpc.example.grpcDemo.GetGeoInfoRequest;
import com.grpc.example.grpcDemo.GetGeoInfoResponse;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@GRpcService
@Component
public class GeoGrpcService  extends GeoServiceGrpc.GeoServiceImplBase {

    private final GrpcDaoService grpcDaoService;

    @Autowired
    public GeoGrpcService(GrpcDaoService grpcDaoService) {
        this.grpcDaoService = grpcDaoService;
    }

    @Override
    public void getGeoInfo(GetGeoInfoRequest request, StreamObserver<GetGeoInfoResponse> responseObserver) {
        GetGeoInfoResponse geoInfoResponse = grpcDaoService.getById(request.getGeoCode());
        responseObserver.onNext(geoInfoResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllGeo(Empty request, StreamObserver<GetGeoInfoResponse> responseObserver) {
        grpcDaoService.getAll()
                .stream()
                .peek(r ->  {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).forEach(responseObserver::onNext);
        responseObserver.onCompleted();

    }
}