/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.chukwa.datacollection.writer;

import java.util.List;
import org.apache.hadoop.chukwa.Chunk;
import org.apache.hadoop.chukwa.datacollection.collector.servlet.LogDisplayServlet;
import org.apache.hadoop.chukwa.datacollection.writer.ChukwaWriter.CommitStatus;
import org.apache.hadoop.conf.Configuration;

public class ExtractorWriter extends PipelineableWriter {

  public static LogDisplayServlet recipient;
  
  @Override
  public void close() throws WriterException {
    next.close();
  }

  @Override
  public void init(Configuration c) throws WriterException {
  }
  
  public CommitStatus add(List<Chunk> chunks) throws WriterException {
    if(recipient != null)
      recipient.add(chunks);
    if (next != null)
      return next.add(chunks); //pass data through
    else
      return ChukwaWriter.COMMIT_OK;
  }

}
