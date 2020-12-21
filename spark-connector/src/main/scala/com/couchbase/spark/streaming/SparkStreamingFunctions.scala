/*
 * Copyright (c) 2015 Couchbase, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.couchbase.spark.streaming

import com.couchbase.client.dcp.{StreamFrom, StreamTo}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.ReceiverInputDStream

class SparkStreamingFunctions(@transient val ssc: StreamingContext) extends Serializable {

  def couchbaseStream(
    bucketName: Option[String] = None,
    scopeName: Option[String] = None,
    collectionName: Option[String] = None,
    storageLevel: StorageLevel = StorageLevel.MEMORY_AND_DISK_SER_2,
    from: StreamFrom = StreamFrom.NOW,
    to: StreamTo = StreamTo.INFINITY
  ): ReceiverInputDStream[StreamMessage] =
    new CouchbaseInputDStream(ssc, storageLevel, bucketName, scopeName, collectionName, from, to)

}
