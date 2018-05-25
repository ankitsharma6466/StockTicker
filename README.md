# Stock Tracker

Simple application that displays current stat for a particular stock. 
*Google is set by default*

## functionalities
1) Continuously monitors a stock
2) Stays upto date with optimised background refreshes
3) Stock can be changed from settings

## Basic architecture overview
1) Complete app written in kotlin
2) Based on MVVM architecture
3) Uses Android Architecture components such as LiveData, ViewModel, Room, Lifecycles
4) Uses Dependency Injection using Dagger 2 and Dagger Android

## Directory structure

#### common - Contains base classes, utility classes, helpers and factory methods

#### data
        db - Database related files like DAO
        events - contains events that will emitted for observer pattern implementation
        models - Contains POJO's(Data containers) used throughout the app
        repositories - Data source wrapper classes
        services - contains data providing and processing services

#### di - contains dependency injection setup files
        annotations - contains custom annotations
        modules - contains dependency providers and other abstraction modules

#### views - contains further packages and files respective to each view
