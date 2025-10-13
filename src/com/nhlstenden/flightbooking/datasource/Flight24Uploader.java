package com.nhlstenden.flightbooking.datasource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class Flight24Uploader
{
    private Set<FlightDataSource> dataSources;

    public Flight24Uploader()
    {
        this.dataSources = new HashSet<>();
    }

    public Set<FlightDataSource> getDataSources()
    {
        return this.dataSources;
    }

    public void addDataSource(FlightDataSource flightDataSource)
    {
        this.dataSources.add(flightDataSource);
    }

    private String getContents()
    {
        StringBuilder contents = new StringBuilder();

        for (FlightDataSource dataSource : this.dataSources)
        {
            contents.append("\n").append(dataSource.getData());
        }

        return contents.toString();
    }

    public void upload()
    {
        Path filePath = Path.of("output.txt");

        try {
            Files.writeString(filePath, this.getContents());
            System.out.println("File saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
