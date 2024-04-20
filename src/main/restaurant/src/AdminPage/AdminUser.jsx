import React, { useEffect, useState } from 'react';
import axios from 'axios';

export default function AdminUser() {
  const [tableData, setTableData] = useState([]);
  const [searchFilter, setSearchFilter] = useState('id');
  const [searchWord, setSearchWord] = useState('');
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const recordsPerPage = 10;

  // 백엔드에서 데이터를 가져오는 함수
  const fetchData = () => {
    axios.get(`http://localhost:8080/api/admin/user`, {
      params: {
        type: searchFilter,
        word: searchWord,
        nowPage: currentPage,
        recordPerPage: recordsPerPage
      }
    })
      .then((res) => {
        setTableData(res.data.users);
        setTotalPages(res.data.totalPages);
      })
      .catch((error) => {
        console.error("데이터를 불러오는 데 실패했습니다.", error);
      });
  };

  // 데이터, 검색 필터, 페이지가 변경될 때 데이터를 다시 가져옴
  useEffect(() => {
    fetchData();
  }, [currentPage, searchFilter, searchWord]);

  // 검색 처리 함수
  const searchHandler = () => {
    const filters = {
      id: (v) => v.id == searchWord,
      email: (v) => v.email.includes(searchWord),
      name: (v) => v.name.includes(searchWord),
      phone: (v) => v.phone.includes(searchWord)
    };

    setTableData(tableData.filter(filters[searchFilter]));
    setCurrentPage(1); // 검색할 때 페이지를 1로 리셋
  };

  // 페이지 변경 처리 함수
  const handlePageChange = (page) => {
    setCurrentPage(page);
  };

  return (
    <div className="admin-user">
      <div className="admin-search">
        <select name="filter" value={searchFilter} onChange={(e) => setSearchFilter(e.target.value)}>
          <option value="id">고객ID</option>
          <option value="email">이메일</option>
          <option value="name">이름</option>
          <option value="phone">전화번호</option>
        </select>
        <input
          type="text"
          placeholder="검색어 입력"
          value={searchWord}
          onChange={(e) => setSearchWord(e.target.value)}
        />
        <button onClick={searchHandler}>검색</button>
      </div>
      <table className="admin-user-list">
        <thead>
          <tr className="admin-header">
            <th>고객ID</th>
            <th>이메일</th>
            <th>비밀번호</th>
            <th>이름</th>
            <th>전화번호</th>
            <th>포인트</th>
          </tr>
        </thead>
        <tbody>
          {tableData.length > 0 ? (
            tableData.map((a) => (
              <tr className="admin-data" key={a.id}>
                <td>{a.id}</td>
                <td>{a.email}</td>
                <td>{a.password}</td>
                <td>{a.username}</td>
                <td>{a.phone}</td>
                <td>{a.point}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="6">데이터가 없습니다.</td>
            </tr>
          )}
        </tbody>
      </table>
      <div className="pagination-container">
        <button
          className="page-button"
          disabled={currentPage === 1}
          onClick={() => currentPage > 1 && handlePageChange(currentPage - 1)}
        >
          이전
        </button>
        {Array.from({ length: totalPages }).map((_, index) => (
          <button
            key={index}
            className={`page-button ${currentPage === index + 1 ? 'active' : ''}`}
            onClick={() => handlePageChange(index + 1)}
          >
            {index + 1}
          </button>
        ))}
        <button
          className="page-button"
          disabled={currentPage === totalPages}
          onClick={() => currentPage < totalPages && handlePageChange(currentPage + 1)}
        >
          다음
        </button>
      </div>
    </div>
  );
}
