이 프로젝트에서는 특정 이벤트를 처리하는 API 를 개발했다.

시간 부족으로 ConverterNotFoundException 을 해결하지 못 해, 빌드는 되지만 run 이 안 되는 상태.

이벤트에 담긴 정보는 `reviewId`, `attachedPhotoIds`, `userId`, `placeId` 외 여러 정보가 있다.
위 정보로 판단할 때, 이미 리뷰 데이터, 사진 데이터, 사용자 데이터, 장소 도메인은 이미 이 프로젝트 외부에 존재한다.
그렇기 때문에 위 데이터 관련 개발은 이 프로젝트의 대상이 아니다.

이벤트 중 `action`의 종류에는 `ADD`, `MOD`, `DELETE`가 있다.
성격상 `MOD`와 `DELETE`에는 반드시 `ADD`가 선행한다.

`ADD` 이벤트는 review 도메인에서 리뷰가 생성된 경우에 발생하며, 파라미터는 예제와 같다.

`DELETE` 이벤트는 review 도메인에서 리뷰가 삭제된 경우에 발생하며, reviewId 을 pathVariable 로 사용한다.

`MOD` 이벤트는 review 도메인에서 리뷰 사진을 변경한 경우에 발생하며, `reviewId` 을 pathVariable 로 사용하고 `attachedPhotoIds` 프로퍼티는 변경된 사진 정보다.
요청에서 `attachedPhotoIds` 가 null 이면 존재하던 사진을 모두 삭제한 경우다. 
요청에서 `attachedPhotoIds` 가 null 이 아니면 없던 사진을 추가한 경우다. 
이미 사진이 있던 리뷰를 수정해 사진이 계속 존재하는 경우는 이 프로젝트에서 다루지 않는다. 왜냐하면 이는 review 도메인에서 처리할 문제이기 때문이다.
