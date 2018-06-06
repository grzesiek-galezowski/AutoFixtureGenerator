package autofixture.specification.acceptance.testfixtures;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public final class Group {
    private List<GroupMember> members;

    public Group(Group toCopy) {
        members = new ArrayList<>(toCopy.getMembers());
    }
}