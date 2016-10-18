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
package org.camunda.bpm.engine.impl.pvm.runtime.operation;

import static org.camunda.bpm.engine.impl.util.ActivityBehaviorUtil.getActivityBehavior;

import org.camunda.bpm.engine.impl.bpmn.behavior.ActivityInstanceAssumption;
import org.camunda.bpm.engine.impl.bpmn.behavior.FlowNodeActivityBehavior;
import org.camunda.bpm.engine.impl.pvm.PvmActivity;
import org.camunda.bpm.engine.impl.pvm.PvmException;
import org.camunda.bpm.engine.impl.pvm.PvmLogger;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityBehavior;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.runtime.PvmExecutionImpl;

/**
 * @author Tom Baeyens
 */
public class PvmAtomicOperationActivityLeave implements PvmAtomicOperation {

  private final static PvmLogger LOG = PvmLogger.PVM_LOGGER;

  public boolean isAsync(PvmExecutionImpl execution) {
    return false;
  }

  public void execute(PvmExecutionImpl execution) {
    // TODO: replace behavior#leave everywhere with execution#leaveActivity, such that this operation is used

    FlowNodeActivityBehavior activityBehavior = (FlowNodeActivityBehavior) getActivityBehavior(execution);

    ActivityInstanceAssumption assumption = ActivityInstanceAssumption.getCurrentAssumption();

    if (assumption.assume(execution)) {
      try {
        activityBehavior.leave(execution);
      } catch (RuntimeException e) {
        throw e;
      } catch (Exception e) {
        throw new PvmException("couldn't leave activity", e);
      }
    }

    // TODO: instanceofs

  }

  public String getCanonicalName() {
    return "activity-leave";
  }

  public boolean isAsyncCapable() {
    return false;
  }
}