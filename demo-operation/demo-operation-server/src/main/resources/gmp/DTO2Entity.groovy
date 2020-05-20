package gmp

import com.hwj.demo.operation.base.entity.Org
import com.hwj.demo.operation.server.entity.OrgUserEntity
import com.hwj.demo.operation.server.web.dto.AuthUserDTO
import com.hwj.demo.operation.server.web.dto.OrgDTO
import com.hwj.demo.user.authority.api.dto.UserApiDTO
import com.hwj.demo.user.base.entity.User
import com.hwj.demo.user.server.web.dto.UserDTO


mappers {

    mapper {
        from OrgDTO to Org converter { OrgDTO dto, Org org ->
            org.parentId = dto.parentId
            org.orgCode = dto.orgCode
            org.orgName = dto.orgName
            org.orgType = dto.orgType
            org.description = dto.description
        }
    }

    mapper {
        from UserDTO to User converter { UserDTO dto, User user ->
            user.recId = dto.recId
            user.code = dto.code
            user.name = dto.name
            user.password = dto.password
            user.mobile = dto.mobile
            user.email = dto.email
            user.enabled = dto.enabled
            user.internal = dto.internal
            user.recCreatedBy = dto.recCreatedBy
            user.recCreatedOrg = dto.recCreatedOrg
            user.recCreatedTime = dto.recCreatedTime
            user.recModifiedBy = dto.recModifiedBy
            user.recModifiedOrg = dto.recModifiedOrg
            user.recModifiedTime = dto.recModifiedTime
            user.recVersion = dto.recVersion
        }
    }

    mapper {
        from AuthUserDTO to OrgUserEntity converter { AuthUserDTO dto, OrgUserEntity orgUser ->
            orgUser.orgId = dto.orgId
            orgUser.userId = dto.recId
            orgUser.userCode = dto.code
            orgUser.userName = dto.name
            orgUser.userApiDTO = new UserApiDTO(
                    recId: dto.recId,
                    code: dto.code,
                    name: dto.name,
                    mobile: dto.mobile,
                    email: dto.email,
                    enabled: dto.enabled,
                    recCreatedBy: dto.recCreatedBy,
                    recCreatedOrg: dto.recCreatedOrg,
                    recCreatedTime: dto.recCreatedTime,
                    recModifiedBy: dto.recModifiedBy,
                    recModifiedOrg: dto.recModifiedOrg,
                    recModifiedTime: dto.recModifiedTime,
                    recVersion: dto.recVersion
            )
            orgUser.org = new Org(
                    recId: dto.orgId,
                    orgCode: dto.orgCode,
                    orgName: dto.orgName
            )
        }
    }

    mapper {
        from UserApiDTO to User converter { UserApiDTO dto, User user ->
            user.recId = dto.recId
            user.code = dto.code
            user.name = dto.name
            user.password = dto.password
            user.mobile = dto.mobile
            user.email = dto.email
            user.enabled = dto.enabled
            user.internal = dto.internal
            user.recCreatedBy = dto.recCreatedBy
            user.recCreatedOrg = dto.recCreatedOrg
            user.recCreatedTime = dto.recCreatedTime
            user.recModifiedBy = dto.recModifiedBy
            user.recModifiedOrg = dto.recModifiedOrg
            user.recModifiedTime = dto.recModifiedTime
            user.recVersion = dto.recVersion
        }
    }


}

