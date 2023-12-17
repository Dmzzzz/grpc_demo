package com.grpc.example.grpcDemo.grpc;

import com.grpc.example.grpcDemo.GetGeoInfoRequest;
import com.grpc.example.grpcDemo.GetGeoInfoResponse;
import io.grpc.stub.StreamObserver;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class MyGrpcAspect {


/*    @Around(value = "execution(* com.grpc.example.grpcDemo.grpc.GeoGrpcService.*.*(request, responseObserver))")
    public Object aroundGetGeoInfo(final ProceedingJoinPoint proceedingJoinPoint,
                                   final GetGeoInfoRequest request,
                                   final StreamObserver<GetGeoInfoResponse> responseObserver) throws Throwable {


        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        Object invocationResult = null;
        Throwable invockationThrew = null;
        try {
            invocationResult = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        } catch (Throwable e) {
            invocationResult = e;
        }

        return invocationResult;

    }*/

 /*   @Around(value = "execution(* com.grpc.example.grpcDemo.grpc.GrpcDaoService.getAll())")
    public Object aroundDao(final ProceedingJoinPoint proceedingJoinPoint) {

        String str = "a";
        System.out.println("HELLO");
        Object invocationResult = null;
        Throwable invockationThrew = null;
        try {
            invocationResult = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        } catch (Throwable e) {
            invocationResult = e;
        }

        return invocationResult;

    }*/

    @Pointcut("execution(* com.grpc.example.grpcDemo.advice.ActorRepository.*(..))")
    public void methodsAcceptingEntities() {
    }

    @Pointcut("execution(* com.grpc.example.grpcDemo.grpc.GeoGrpcService.*(..))")
    public void methodsAcceptingGrpc() {
    }



    @Around("methodsAcceptingEntities()")
    public void allDaoAddMethods(JoinPoint joinPoint) {
        System.out.println("Intercepted method: " + joinPoint);
        System.out.println("Arguments: " + joinPoint.getArgs());
        System.out.println(joinPoint.getTarget());
    }

    @Around("methodsAcceptingGrpc()")
    public void allGrpc(ProceedingJoinPoint  joinPoint) {
        System.out.println("Intercepted method: " + joinPoint);
        System.out.println("Arguments: " + joinPoint.getArgs());
        System.out.println(joinPoint.getTarget());

        try {
            joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}