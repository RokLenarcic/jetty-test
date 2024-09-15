# Example project for Jetty connection resets

## Why

I've tried some stress testing of a jetty server and I couldn't understand why I kept getting `apr_socket_recv: Connection reset by peer (54)` error.

The error happens sometimes, but not always. I am sure there's a reasonable explanation, but I don't see it.

## How to test

Run the main class ExampleJetty and then run 

```
ab -k -n 10000 -c 200 http://localhost:4003/xx
```

If I run this command 10 times, about 5 times I get the expected result:

```
roklenarcic@Roks-MBP-2 portal % ab -k -n 10000 -c 100 http://localhost:4003/xx
This is ApacheBench, Version 2.3 <$Revision: 1913912 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 1000 requests
Completed 2000 requests
Completed 3000 requests
Completed 4000 requests
Completed 5000 requests
Completed 6000 requests
Completed 7000 requests
Completed 8000 requests
Completed 9000 requests
Completed 10000 requests
Finished 10000 requests


Server Software:        
Server Hostname:        localhost
Server Port:            4003

Document Path:          /xx
Document Length:        176 bytes

Concurrency Level:      100
Time taken for tests:   3.943 seconds
Complete requests:      10000
Failed requests:        0
Keep-Alive requests:    10000
Total transferred:      3230000 bytes
HTML transferred:       1760000 bytes
Requests per second:    2536.14 [#/sec] (mean)
Time per request:       39.430 [ms] (mean)
Time per request:       0.394 [ms] (mean, across all concurrent requests)
Transfer rate:          799.97 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   7.0      0     103
Processing:    15   38  30.9     19     158
Waiting:       15   38  30.9     19     158
Total:         15   39  31.7     19     191

Percentage of the requests served within a certain time (ms)
  50%     19
  66%     52
  75%     57
  80%     64
  90%     89
  95%    106
  98%    122
  99%    131
 100%    191 (longest request)
```

and about 5 times I get this:

```
roklenarcic@Roks-MBP-2 portal % ab -k -n 10000 -c 100 http://localhost:4003/xx
This is ApacheBench, Version 2.3 <$Revision: 1913912 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
apr_socket_recv: Connection reset by peer (54)
Total of 1 requests completed

```

It always fails to complete hardly any requests when it fails. I don't understand why this is the case.
