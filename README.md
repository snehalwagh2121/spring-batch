# SPRING BATCH

##COMPONENTS

###main components:
1. Job Launcher - Starts job execution
2. Job Repository - Persists job execution matadata
3. Job - represents batch process
4. Step - phases in a job. Job can consist of multiple steps
5. Tasklet, Chunk, ItemReader, ItemProcessor, ItemWriter

###JOB LAUNCHER INERFACE
Entry point to launch a job. The job launcher encapsulates launching stratergies such as executing a job synchronously or asynchronously.

```java
public interface JobLauncher {
public JobExecution run(Job job, JobParameters jobParameters)
throws JobExecutionAlreadyRunningException,
JobRestartException, JobInstanceAlreadyCompleteException,
JobParametersInvalidException;
}
```

run() accepts 2 parameters:
1. Job - this will be a bean
2. JobParameters - created on the fly 

All this is okay, but who calls this jobLauncher? 
<br>
&nbsp;&nbsp;&nbsp;&nbsp;Your own java program can use the jobLauncher to launch a job, but so can cmd line runners or schedulers.
<br><br>
Job Repository gives us all the methods to control the job lifecycle(create, update so on).

Job launcher delegates job creation to Job Repository, and the Job calls jobRepositiry to update its state while execution.

###JOB, JOB INSTANCE AND JOB EXECUTION

We define job as a batch process composed of a sequence of steps.
Job instance as a specific run of a job, job execution as execution of a job instance(with success or failure).

We can specify date of the job instance like:
jobLauncher.run(job , new JobParametersBuilder()
            .addString("date", "2023-03-07")
            .toJobParameters()
            )

JOB INSTANCE= JOB + JOB PARAMETER

###STEPS

It is a self contained unit of work that is the main building block of a job.
There are 2 main types of steps
1. Tasklet step
2. Chunk based step.

###LIFECYCLE OF A JOB INSTANCE AND JOB EXECUTION

- When you launch a job for the first time, Spring Batch creates the corresoinding job instance and a first job execution.
- You cant launch the execution of a job instance if the previous execution of the same instance has already completed successfully.
- You cant launch multiple execution of the same instance at the same time.

In tasklet step the job takes a tasklet implementation and runs its execute() method until the scope of a transaction over and over until the execute method tells the step to stop.
It's commonly used for things like initialization, runnning a stored procedure, sending notifications and so on.
 
The chunk-based step is intended for item based processing. Each step has up to 3 main parts: ItemReader, ItemProcessor(optional), ItemWriter.

Job Repository component is responsible for maintaining the state of a job as well as verios performance metrics(start time, end time, status, no of reads/write etc)
As each step is execute, the JobRepository is updated with the current state. What step is executed, its curent statis, how many items were read, processed, written and so on ar all stored in the JobRepository.

JOB -----> JOB INSTANCE ------> JOB EXECUTION

Job Execution is a physical execution of a Job. Everytime you launch a job, u'll get a new job Execution, you may not get a new JobInstance.
e.g: When a job is run the first time, you get a new JobInstance and a JobExecution. If that execution fails, on the restart you wont get a new JobInstance. So a JobInstance can have multiple JobExecution within it.

###ASYNCHRONOUS ITEM PROCESSOR/ITEM WRITER
The AsynchronousItemProcessor is a decorator for an ItemProcessor implementation that executes each call to the ItemProcessor in its own thread.
Instead of returning the result of the ItemProcessor call, it returns a java.util.concurrent.Future for each call.
The list of futures within the current chunk are then handed off to AsynchronousItemWriter. 
## TASKLETS

Tasklets are used in projects where there is a need to process the data and not to read/write into a file. Although we
can read and write into a file, but it will be done in one transaction. So if any issues come with just one record, we
would have to run the batch job for the whole data. Also, it is majorly used when we are dealing with small records.

[for indepth details please click on this](https://levelup.gitconnected.com/10-handy-spring-batch-tricks-24556cf549a4)

