/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.engine.impl.externaltask;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.List;

/**
 * @author Thorben Lindhauer
 *
 */
public class TopicFetchInstruction implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String topicName;
  protected AbstractMap.SimpleEntry<String, String> topicBusinessKeyTuple;
  protected List<String> variablesToFetch;
  protected long lockDuration;
  protected boolean deserializeVariables = false;

  public TopicFetchInstruction(String topicName, long lockDuration) {
    this.topicName = topicName;
    this.topicBusinessKeyTuple = new AbstractMap.SimpleEntry<String, String>(topicName, null);
    this.lockDuration = lockDuration;
  }

  public List<String> getVariablesToFetch() {
    return variablesToFetch;
  }

  public void setVariablesToFetch(List<String> variablesToFetch) {
    this.variablesToFetch = variablesToFetch;
  }

  public void setBusinessKey(String businessKey) {
    this.topicBusinessKeyTuple.setValue(businessKey);
  }

  public Long getLockDuration() {
    return lockDuration;
  }

  public String getTopicName() {
    return topicName;
  }

  public AbstractMap.SimpleEntry<String, String> getTopicBusinessKeyTuple() {
    return topicBusinessKeyTuple;
  }

  public boolean isDeserializeVariables() {
    return deserializeVariables;
  }

  public void setDeserializeVariables(boolean deserializeVariables) {
    this.deserializeVariables = deserializeVariables;
  }

}
